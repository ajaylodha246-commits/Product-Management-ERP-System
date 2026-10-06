package com.codeonet.product_managenent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SalesOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;

    private Integer quantity;

    private Integer soldQuantity;

    @JsonBackReference
    @ManyToOne
    private Sales_Order sales_order;
}
