package com.codeonet.product_managenent.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String subCategoryName;

    @ManyToOne
    private Category category;
}
