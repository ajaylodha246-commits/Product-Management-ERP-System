package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.dto.SalesRequest;
import com.codeonet.product_managenent.dto.SalesItemUpdateDto;
import com.codeonet.product_managenent.entity.Inventory;
import com.codeonet.product_managenent.entity.Sales_Order;
import com.codeonet.product_managenent.service.Sales_OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class Sales_orderController {

    @Autowired
    private Sales_OrderService service;

    @PostMapping("/add")
    public Sales_Order soldProduct(@RequestBody SalesRequest request, Authentication authentication){

        String username = authentication.getName();

        return service.salesRecord(request,username);
    }

    @GetMapping("/getAll")
    public List<Sales_Order> getAll(){
        return service.getAllSales();
    }


    @GetMapping("/categories")
    public List<String> getCategories() {
        return service.getCategories();
    }

    @GetMapping("/subcategories")
    public List<String> getSubCategories(
            @RequestParam String category) {

        return service.getSubCategories(category);
    }

    @GetMapping("/products")
    public List<String> getProducts(
            @RequestParam String category,
            @RequestParam String subCategory) {

        return service.getProducts(category, subCategory);
    }

    @GetMapping("/sizes")
    public List<Inventory> getSizes(
            @RequestParam String productName) {

        return service.getSizes(productName);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        service.deleteById(id);
        return "Deleted";
    }

    @PutMapping("/soldStock")
    public Sales_Order soldStock(@RequestParam Long id, @RequestParam Integer quantity){
        return service.updateSoldStock(id, quantity);
    }
}
