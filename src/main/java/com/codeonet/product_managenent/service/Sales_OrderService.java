package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.dto.SalesRequest;
import com.codeonet.product_managenent.dto.SalesItemUpdateDto;
import com.codeonet.product_managenent.entity.Inventory;
import com.codeonet.product_managenent.entity.Sales_Order;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface Sales_OrderService {

    public Sales_Order salesRecord(SalesRequest request,String username);
    public List<Sales_Order> getAllSales();

    List<String> getCategories();

    List<String> getSubCategories(String category);

    List<String> getProducts(String category, String subCategory);

    List<Inventory> getSizes(String productName);

    public void deleteById(Long id);

    public Sales_Order updateSoldStock( Long id, Integer quantity);
}
