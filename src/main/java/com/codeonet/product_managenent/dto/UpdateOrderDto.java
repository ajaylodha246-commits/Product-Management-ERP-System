package com.codeonet.product_managenent.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateOrderDto {

    private Long vendorId;

    private double purchasePrice;

    private List<OrderItemDto> orderItems;

}
