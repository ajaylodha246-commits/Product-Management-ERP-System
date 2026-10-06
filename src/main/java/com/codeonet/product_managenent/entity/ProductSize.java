package com.codeonet.product_managenent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;

    @JsonBackReference
    @ManyToOne
    private Products product;

    @Column(nullable = false)
    private Integer stock = 0;

}
