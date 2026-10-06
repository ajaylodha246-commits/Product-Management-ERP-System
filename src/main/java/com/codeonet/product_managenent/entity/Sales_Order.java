package com.codeonet.product_managenent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Sales_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Inventory inventory;

    private double sellingPrice;

    @ManyToOne
    private Platform platform;

    @ManyToOne
    private User salesBy;

    @JsonManagedReference
    @OneToMany(mappedBy = "sales_order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SalesOrderItem> orderItems = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime salesDate;
}
