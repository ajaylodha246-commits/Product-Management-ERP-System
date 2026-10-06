package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.entity.Category;
import com.codeonet.product_managenent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/getAll")
    public List<Category> getAll(){
        return service.findAllCategory();
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category){
        return service.saveCategory(category);
    }
}
