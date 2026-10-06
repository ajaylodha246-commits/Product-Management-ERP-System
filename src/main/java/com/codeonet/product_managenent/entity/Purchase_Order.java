package com.codeonet.product_managenent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Purchase_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Products products;

    @ManyToOne
    private Vendor vendor;

    private double purchasePrice;

    @ManyToOne
    private User orderBy;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.INITIATED;

    @JsonManagedReference
    @OneToMany(mappedBy = "purchase_order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @CreationTimestamp
    private LocalDateTime orderDate;
}
