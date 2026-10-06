package com.codeonet.product_managenent.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseRequest {

    private Long productId;

    private Long vendorId;

//    private int productsQuantity;

    private double purchasePrice;

    private LocalDateTime orderDate;

    private List<OrderItemDto> orderItems;

}
