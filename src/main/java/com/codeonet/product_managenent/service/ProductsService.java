package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.dto.ProductsRequest;
import com.codeonet.product_managenent.dto.ProductUpdateDTO;
import com.codeonet.product_managenent.entity.Products;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
    public List<Products> searchByCategory(String category);
    public Products addProducts( ProductsRequest request,String username);
    public List<Products> getAllProducts();
    public Products byId(Long id);
    public void gotDelete(Long id);
    public Optional<Products> searchByName(String productName);
    public void updateProduct(Long id, ProductUpdateDTO dto);
    Products updateImage(Long id, MultipartFile image);
}
