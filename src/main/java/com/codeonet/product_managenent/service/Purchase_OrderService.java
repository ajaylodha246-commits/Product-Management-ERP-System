package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.dto.PurchaseRequest;
import com.codeonet.product_managenent.dto.UpdateOrderDto;
import com.codeonet.product_managenent.entity.OrderStatus;
import com.codeonet.product_managenent.entity.Purchase_Order;

import java.util.List;

public interface Purchase_OrderService {

    public Purchase_Order addPurchaseOrder(PurchaseRequest request, String username);
    public List<Purchase_Order> getAllPurchaseOrder();
    public Purchase_Order updateStatus(Long id, OrderStatus status);
    public Purchase_Order updatePurchaseOrder(Long id, UpdateOrderDto request);
    }
