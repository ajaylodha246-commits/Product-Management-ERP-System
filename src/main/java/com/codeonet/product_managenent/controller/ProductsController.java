package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.dto.ProductsRequest;
import com.codeonet.product_managenent.dto.ProductUpdateDTO;
import com.codeonet.product_managenent.entity.Products;
import com.codeonet.product_managenent.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @PostMapping("/add")
    public Products add(@ModelAttribute ProductsRequest request, Authentication authentication) {
      String  username = authentication.getName();
        return service.addProducts(request,username);
    }


    @GetMapping("/getAll")
    public List<Products> getAll() {
        return service.getAllProducts();
    }
    @GetMapping("/getById/{id}")
    public Products GetByID(@PathVariable Long id){
        return service.byId(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
         service.gotDelete(id);
        return "Deleted Products with id: "+ id;

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDTO dto){

        service.updateProduct(id,dto);

        return ResponseEntity.ok("Product Updated Successfully");
    }

    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Products updateImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image) {

        return service.updateImage(id, image);
    }

    @GetMapping("/getByCategory/{category}")
    public List<Products> getByCategory(@PathVariable String category){
        return service.searchByCategory(category);
    }

    @GetMapping("/getByName/{productName}")
    public Optional<Products> getByName(@PathVariable String productName){
        return service.searchByName(productName);
    }

}
