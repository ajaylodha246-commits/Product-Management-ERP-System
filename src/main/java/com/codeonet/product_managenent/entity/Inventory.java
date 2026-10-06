package com.codeonet.product_managenent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String description;

    private String category;

    private String subCategory;

    @JsonManagedReference
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<InventorySizeperstock> sizeStocks = new ArrayList<>();

//    @Transient
//    public Integer getTotalStock() {
//        if (sizeStocks == null || sizeStocks.isEmpty()) {
//            return 0;
//        }
//
//        return sizeStocks.stream()
//                .mapToInt(stock -> stock.getQuantity() == null ? 0 : stock.getQuantity())
//                .sum();
//    }

}
