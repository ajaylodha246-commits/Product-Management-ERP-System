package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.entity.SubCategory;
import com.codeonet.product_managenent.repository.SubCategoryRepository;
import com.codeonet.product_managenent.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository repo;

    @Override
    public List<SubCategory> findAllSubCategory(){
        return repo.findAll();
    }
    @Override
    public SubCategory saveSubCategory(SubCategory subCategory){
        return repo.save(subCategory);
    }
}
