package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.Purchase_Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Purchase_OrderRepository extends JpaRepository<Purchase_Order, Long> {
}
