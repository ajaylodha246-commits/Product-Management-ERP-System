package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    // Optional<SubCategory> findBySubCategoryName(String subCategoryName);

    Optional<SubCategory> findBySubCategoryNameAndCategory_CategoryName(
            String subCategoryName,
            String categoryName
    );
}
