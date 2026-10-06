package com.codeonet.product_managenent.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class   ProductsRequest {

    private String productName;
    private String description;

    private String categoryName;
    private String subCategoryName;
    private MultipartFile image;

    private List<String> sizes;

    private LocalDateTime addedDate;
}
