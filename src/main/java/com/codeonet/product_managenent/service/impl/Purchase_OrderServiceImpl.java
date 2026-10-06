package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.dto.OrderItemDto;
import com.codeonet.product_managenent.dto.PurchaseRequest;
import com.codeonet.product_managenent.dto.UpdateOrderDto;
import com.codeonet.product_managenent.entity.*;
import com.codeonet.product_managenent.repository.*;
import com.codeonet.product_managenent.service.Purchase_OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Purchase_OrderServiceImpl implements Purchase_OrderService {

    @Autowired
    private Purchase_OrderRepository purchaseRepo;

    @Autowired
    private InventoryRepository inventoryRepo;

    @Autowired
    private ProductSizeRepository productSizeRepo;

    @Autowired
    private ProductsRepository productsRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VendorRepository vendorRepo;

    @Override
    public Purchase_Order addPurchaseOrder(PurchaseRequest request, String username) {

        Products product = productsRepo.findById(request.getProductId()).orElseThrow();

        productsRepo.save(product);

        Vendor vendor = vendorRepo.findById(request.getVendorId()).orElseThrow();

        User user = userRepo.findByUsername(username)
                .orElseThrow();

        Purchase_Order purchase_order = new Purchase_Order();

        purchase_order.setVendor(vendor);
        purchase_order.setProducts(product);
        purchase_order.setPurchasePrice(request.getPurchasePrice());
        purchase_order.setOrderBy(user);

        Purchase_Order savedOrder =
                purchaseRepo.save(purchase_order);

        for (OrderItemDto dto : request.getOrderItems()) {

            ProductSize productSize =
                    productSizeRepo
                            .findById(dto.getProductSizeId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Size not found"));

            productSize.setStock(productSize.getStock() + dto.getQuantity());
            productSizeRepo.save(productSize);

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setPurchase_order(savedOrder);

            orderItem.setSize(productSize);

            orderItem.setQuantity(
                    dto.getQuantity());

            orderItemRepo.save(orderItem);
        }

        return savedOrder;
    }

    public List<Purchase_Order> getAllPurchaseOrder() {
        return purchaseRepo.findAll();
    }

    @Override
    @Transactional
    public Purchase_Order updateStatus(Long id, OrderStatus status) {

        Purchase_Order order = purchaseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        OrderStatus oldStatus = order.getStatus();

        order.setStatus(status);

        Purchase_Order savedOrder = purchaseRepo.save(order);

        if (oldStatus != OrderStatus.COMPLETED &&
                status == OrderStatus.COMPLETED) {

            Products product = savedOrder.getProducts();

            Inventory inventory = inventoryRepo
                    .findByProductNameAndCategoryAndSubCategory(
                            product.getProductName(),
                            product.getCategory().getCategoryName(),
                            product.getSubcategory().getSubCategoryName()
                    )
                    .orElseGet(Inventory::new);

            if (inventory.getId() == null) {
                inventory.setProductName(product.getProductName());
                inventory.setDescription(product.getDescription());
                inventory.setCategory(product.getCategory().getCategoryName());
                inventory.setSubCategory(product.getSubcategory().getSubCategoryName());
            }

            for (OrderItem item : savedOrder.getOrderItems()) {
                String sizeName = item.getSize().getSize();
                Integer qty = item.getQuantity();

                InventorySizeperstock sizeStock = inventory.getSizeStocks().stream()
                        .filter(stock -> sizeName.equals(stock.getSize()))
                        .findFirst()
                        .orElseGet(() -> {
                            InventorySizeperstock newStock = new InventorySizeperstock();
                            newStock.setSize(sizeName);
                            newStock.setQuantity(0);
                            newStock.setInventory(inventory);
                            inventory.getSizeStocks().add(newStock);
                            return newStock;
                        });

                sizeStock.setQuantity(sizeStock.getQuantity() + qty);
            }

            inventoryRepo.save(inventory);
        }

        return savedOrder;
    }

    @Override
    public Purchase_Order updatePurchaseOrder(Long id, UpdateOrderDto request) {

        Purchase_Order order = purchaseRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));

        order.setVendor(vendorRepo.findById(request.getVendorId()).orElseThrow(() -> new RuntimeException("Vendor not found")));
        order.setPurchasePrice(request.getPurchasePrice());

        for (OrderItemDto dto : request.getOrderItems()) {

            OrderItem item = order.getOrderItems().stream()
                    .filter(i -> i.getSize().getId().equals(dto.getProductSizeId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Size not found"));

            item.setQuantity(dto.getQuantity());
        }


        return purchaseRepo.save(order);
    }
}