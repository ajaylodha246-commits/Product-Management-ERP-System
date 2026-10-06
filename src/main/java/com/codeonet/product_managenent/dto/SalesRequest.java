package com.codeonet.product_managenent.dto;

import lombok.Data;

import java.util.List;

@Data
public class SalesRequest {

    private Long inventoryId;

    private double sellingPrice;

    private Long platformId;

    private List<SalesItemDto> orderItems;
}
