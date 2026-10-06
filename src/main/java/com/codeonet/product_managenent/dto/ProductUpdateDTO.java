package com.codeonet.product_managenent.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateDTO {

    private String description;

    private List<String> newSizes;

    // Getter Setter
}
