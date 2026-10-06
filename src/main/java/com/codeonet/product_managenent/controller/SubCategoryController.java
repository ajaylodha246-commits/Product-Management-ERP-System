package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.entity.SubCategory;
import com.codeonet.product_managenent.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService service;

    @GetMapping("/getAll")
    public List<SubCategory> getAllSubCategory(){
        return service.findAllSubCategory();
    }

    @PostMapping("/add")
    public SubCategory addSubCategory(@RequestBody SubCategory subCategory){
        return service.saveSubCategory(subCategory);
    }
}
