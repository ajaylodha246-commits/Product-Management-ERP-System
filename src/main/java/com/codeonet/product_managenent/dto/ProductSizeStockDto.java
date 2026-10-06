package com.codeonet.product_managenent.dto;

public class ProductSizeStockDto {

    private Long id;
    private Long productId;
    private String productName;
    private String description;
    private String categoryName;
    private String size;
    private Integer stock;

    public ProductSizeStockDto() {
    }

    public ProductSizeStockDto(Long id, Long productId, String productName, String description, String categoryName, String size, Integer stock) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.size = size;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
