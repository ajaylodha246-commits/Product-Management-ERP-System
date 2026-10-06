package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.dto.ProductsRequest;
import com.codeonet.product_managenent.dto.ProductUpdateDTO;
import com.codeonet.product_managenent.entity.*;
import com.codeonet.product_managenent.repository.*;
import com.codeonet.product_managenent.service.ProductsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public Products addProducts(ProductsRequest request, String username) {

        Category category = categoryRepository
                .findByCategoryName(request.getCategoryName())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        SubCategory subCategory = subCategoryRepository
                .findBySubCategoryNameAndCategory_CategoryName(
                        request.getSubCategoryName(),
                        request.getCategoryName())
                .orElseThrow(() ->
                        new RuntimeException("SubCategory not found"));

        if (!subCategory.getCategory().getId().equals(category.getId())) {
            throw new RuntimeException("SubCategory does not belong to Category");
        }

        User user = userRepo.findByUsername(username)
                .orElseThrow();

        Products product = new Products();

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setSubcategory(subCategory);
        product.setAddedBy(user);

        MultipartFile image = request.getImage();

        if (image != null && !image.isEmpty()) {

            try {

                String uploadDir = "uploads/products/";

                File folder = new File(uploadDir);

                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String fileName =
                        System.currentTimeMillis() + "_" + image.getOriginalFilename();

                Path path = Paths.get(uploadDir + fileName);

                Files.copy(image.getInputStream(),
                        path,
                        StandardCopyOption.REPLACE_EXISTING);

                product.setImage(fileName);

            } catch (IOException e) {

                throw new RuntimeException("Image Upload Failed");
            }

        }

        Products savedProduct = productsRepo.save(product);


        if (request.getSizes() != null) {

            for (String size : request.getSizes()) {

                ProductSize productSize = new ProductSize();

                productSize.setSize(size);

                productSize.setProduct(savedProduct);

                productSizeRepository.save(productSize);
            }

        }

        return savedProduct;
    }

    @Override
    public Products updateImage(Long id, MultipartFile image) {

        Products product = productsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Please select an image");
        }

        try {

            String uploadDir = "uploads/products/";

            File folder = new File(uploadDir);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Delete old image if it exists
            if (product.getImage() != null) {

                File oldImage = new File(uploadDir + product.getImage());

                if (oldImage.exists()) {
                    oldImage.delete();
                }
            }

            String fileName =
                    System.currentTimeMillis() + "_" + image.getOriginalFilename();

            Path path = Paths.get(uploadDir + fileName);

            Files.copy(
                    image.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

            product.setImage(fileName);

            return productsRepo.save(product);

        } catch (IOException e) {

            throw new RuntimeException("Image Upload Failed");

        }
    }


    @Override
    public List<Products> getAllProducts() {
        return productsRepo.findAll();
    }

    @Override
    public Products byId(Long id){
        return productsRepo.findById(id).orElse(null);
    }

    @Override
    public void gotDelete(Long id){
        productsRepo.deleteById(id);
    }

    @Override
    public List<Products> searchByCategory(String category){
        return productsRepo.findByCategory_CategoryName(category);
    }
    @Override
    public Optional<Products> searchByName(String productName){
        return productsRepo.findByProductName(productName);
    }
    @Transactional
    public void updateProduct(Long id, ProductUpdateDTO dto){

        Products product = productsRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Not Found"));

        // Update Description
        product.setDescription(dto.getDescription());

        productsRepo.save(product);

        // Add New Sizes
        if(dto.getNewSizes()!=null){

            for(String sizeName : dto.getNewSizes()){

                ProductSize size = new ProductSize();

                size.setSize(sizeName);

                size.setProduct(product);

                productSizeRepository.save(size);
            }

        }

    }


}
