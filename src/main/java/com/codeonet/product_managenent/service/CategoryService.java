package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAllCategory();
    public Category saveCategory(Category category);
}
