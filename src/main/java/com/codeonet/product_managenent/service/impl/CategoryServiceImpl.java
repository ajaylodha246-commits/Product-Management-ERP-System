package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.entity.Category;
import com.codeonet.product_managenent.repository.CategoryRepository;
import com.codeonet.product_managenent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Override
    public List<Category> findAllCategory(){
        return repo.findAll();
    }
    @Override
    public Category saveCategory(Category category){
        return repo.save(category);
    }
}
