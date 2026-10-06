package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {

    public List<SubCategory> findAllSubCategory();
    public SubCategory saveSubCategory(SubCategory subCategory);
}
