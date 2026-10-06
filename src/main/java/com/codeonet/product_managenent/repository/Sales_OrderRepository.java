package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.Sales_Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Sales_OrderRepository extends JpaRepository<Sales_Order, Long> {
}
