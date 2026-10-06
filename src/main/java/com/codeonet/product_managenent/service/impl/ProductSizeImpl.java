package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.dto.ProductSizeStockDto;
import com.codeonet.product_managenent.entity.ProductSize;
import com.codeonet.product_managenent.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSizeImpl {

    @Autowired
    private ProductSizeRepository productSizeRepo;

    public List<ProductSizeStockDto> getAllProductSizes() {
        return productSizeRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ProductSizeStockDto toDto(ProductSize productSize) {
        if (productSize == null) {
            return new ProductSizeStockDto();
        }

        Long productId = productSize.getProduct() != null ? productSize.getProduct().getId() : null;
        String productName = productSize.getProduct() != null ? productSize.getProduct().getProductName() : null;
        String description = productSize.getProduct() != null ? productSize.getProduct().getDescription() : null;
        String categoryName = productSize.getProduct() != null && productSize.getProduct().getCategory() != null
                ? productSize.getProduct().getCategory().getCategoryName()
                : null;

        return new ProductSizeStockDto(
                productSize.getId(),
                productId,
                productName,
                description,
                categoryName,
                productSize.getSize(),
                productSize.getStock()
        );
    }
}
