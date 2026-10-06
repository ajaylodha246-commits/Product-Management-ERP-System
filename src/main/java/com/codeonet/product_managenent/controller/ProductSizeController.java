package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.dto.ProductSizeStockDto;
import com.codeonet.product_managenent.service.impl.ProductSizeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productSize")
public class ProductSizeController {

    @Autowired
    private ProductSizeImpl service;

    @GetMapping("/getAll")
    public List<ProductSizeStockDto> getAllProductSizes() {
        return service.getAllProductSizes();
    }
}
