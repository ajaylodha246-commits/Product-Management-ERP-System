package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.dto.PurchaseRequest;
import com.codeonet.product_managenent.dto.UpdateOrderDto;
import com.codeonet.product_managenent.entity.OrderStatus;
import com.codeonet.product_managenent.entity.Purchase_Order;
import com.codeonet.product_managenent.service.Purchase_OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class Purchase_OrderController {

    @Autowired
    private Purchase_OrderService service;

    @PostMapping("/add")
    public Purchase_Order order(@RequestBody PurchaseRequest request, Authentication authentication){
        String username = authentication.getName();

        return service.addPurchaseOrder(request,username);
    }

    @PutMapping("/{id}/update")
    public Purchase_Order update(@PathVariable Long id , @RequestBody UpdateOrderDto request){
        return service.updatePurchaseOrder(id,request);
    }


    @GetMapping("/getAll")
    public List<Purchase_Order> getAll(){
        return service.getAllPurchaseOrder();
    }

    @PutMapping("/status/{id}")
    public Purchase_Order updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status){

        return service.updateStatus(id, status);
    }

}
