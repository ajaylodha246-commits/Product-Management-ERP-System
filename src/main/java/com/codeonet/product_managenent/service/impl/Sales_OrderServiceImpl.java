package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.dto.SalesItemDto;
import com.codeonet.product_managenent.dto.SalesItemUpdateDto;
import com.codeonet.product_managenent.dto.SalesRequest;
import com.codeonet.product_managenent.entity.*;
import com.codeonet.product_managenent.repository.*;
import com.codeonet.product_managenent.service.Sales_OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class Sales_OrderServiceImpl implements Sales_OrderService {

    @Autowired
    private Sales_OrderRepository salesRepo;

    @Autowired
    private InventoryRepository inventoryRepo;

    @Autowired
    private SalesOrderItemRepository salesOrderItemRepository;

    @Autowired
    private PlatformRepository platformRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    @Override
    public Sales_Order salesRecord(SalesRequest request, String username) {

        Inventory inventory = inventoryRepo.findById(request.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        if (request.getOrderItems() == null || request.getOrderItems().isEmpty()) {
            throw new RuntimeException("At least one size and quantity is required");
        }

        Platform platform = platformRepo.findById(request.getPlatformId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (SalesItemDto dto : request.getOrderItems()) {
            if (dto.getSize() == null || dto.getSize().isBlank()) {
                throw new RuntimeException("Size is required for each row");
            }

            if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
                throw new RuntimeException("Quantity must be greater than zero");
            }

            InventorySizeperstock sizeStock = inventory.getSizeStocks().stream()
                    .filter(stock -> dto.getSize().equals(stock.getSize()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Size not found in inventory: " + dto.getSize()));

            if (sizeStock.getQuantity() == null || sizeStock.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException(
                        "Not enough stock for size " + dto.getSize()
                                + ". Available: " + (sizeStock.getQuantity() == null ? 0 : sizeStock.getQuantity())
                );
            }
        }

        Sales_Order salesOrder = new Sales_Order();
        salesOrder.setInventory(inventory);
        salesOrder.setSalesBy(user);
        salesOrder.setPlatform(platform);
        salesOrder.setSellingPrice(request.getSellingPrice());

        for (SalesItemDto dto : request.getOrderItems()) {
            InventorySizeperstock sizeStock = inventory.getSizeStocks().stream()
                    .filter(stock -> dto.getSize().equals(stock.getSize()))
                    .findFirst()
                    .orElseThrow();

            sizeStock.setQuantity(sizeStock.getQuantity() - dto.getQuantity());

            SalesOrderItem salesOrderItem = new SalesOrderItem();
            salesOrderItem.setSize(dto.getSize());
            salesOrderItem.setQuantity(dto.getQuantity());
            salesOrderItem.setSoldQuantity(0);
            salesOrderItem.setSales_order(salesOrder);
            salesOrder.getOrderItems().add(salesOrderItem);
        }

        inventoryRepo.save(inventory);

        return salesRepo.save(salesOrder);
    }

    @Override
    public List<String> getCategories() {
        return inventoryRepo.getCategories();
    }

    @Override
    public List<String> getSubCategories(String category) {
        return inventoryRepo.getSubCategories(category);
    }

    @Override
    public List<String> getProducts(String category, String subCategory) {
        return inventoryRepo.getProducts(category, subCategory);
    }

    @Override
    public List<Inventory> getSizes(String productName) {
        return inventoryRepo.getSizes(productName);
    }

    @Override
    public List<Sales_Order> getAllSales() {
        return salesRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Sales_Order salesOrder = salesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        Inventory inventory = salesOrder.getInventory();
        if (inventory != null && salesOrder.getOrderItems() != null) {
            for (SalesOrderItem item : salesOrder.getOrderItems()) {
                inventory.getSizeStocks().stream()
                        .filter(stock -> item.getSize().equals(stock.getSize()))
                        .findFirst()
                        .ifPresent(stock -> stock.setQuantity(stock.getQuantity() + item.getQuantity()));
            }
            inventoryRepo.save(inventory);
        }

        salesRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Sales_Order updateSoldStock(Long id, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        SalesOrderItem targetItem = salesOrderItemRepository.findById(id).orElse(null);
        if (targetItem == null) {
            Sales_Order salesOrder = salesRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Sale not found"));

            targetItem = salesOrder.getOrderItems().stream()
                    .filter(item -> {
                        int sold = item.getSoldQuantity() == null ? 0 : item.getSoldQuantity();
                        int total = item.getQuantity() == null ? 0 : item.getQuantity();
                        return sold < total;
                    })
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("All sold quantities are already updated"));
        }

        int sold = targetItem.getSoldQuantity() == null ? 0 : targetItem.getSoldQuantity();
        int total = targetItem.getQuantity() == null ? 0 : targetItem.getQuantity();

        if (sold + quantity > total) {
            throw new RuntimeException("Sold quantity cannot exceed the ordered quantity");
        }

        targetItem.setSoldQuantity(sold + quantity);

        return salesRepo.save(targetItem.getSales_order());
    }

}
