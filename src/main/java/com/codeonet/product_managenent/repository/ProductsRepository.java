package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByProductName(String productName);

    List<Products> findByCategory_CategoryName(String categoryName);

//    List<Products> findBySellingPriceBetween(
//            Double minPrice,
//            Double maxPrice
//    );
}
