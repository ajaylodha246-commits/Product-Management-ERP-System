package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductNameAndCategoryAndSubCategory(
            String productName,
            String category,
            String subCategory
    );

    @Query("""
            SELECT DISTINCT i.category
            FROM Inventory i
            JOIN i.sizeStocks s
            WHERE s.quantity > 0
            """)
    List<String> getCategories();

    @Query("""
            SELECT DISTINCT i.subCategory
            FROM Inventory i
            JOIN i.sizeStocks s
            WHERE i.category = :category
            AND s.quantity > 0
            """)
    List<String> getSubCategories(String category);

    @Query("""
            SELECT DISTINCT i.productName
            FROM Inventory i
            JOIN i.sizeStocks s
            WHERE i.category = :category
            AND i.subCategory = :subCategory
            AND s.quantity > 0
            """)
    List<String> getProducts(String category, String subCategory);

    @Query("""
            SELECT i
            FROM Inventory i
            JOIN i.sizeStocks s
            WHERE i.productName = :productName
            AND s.quantity > 0
            """)
    List<Inventory> getSizes(String productName);
}
