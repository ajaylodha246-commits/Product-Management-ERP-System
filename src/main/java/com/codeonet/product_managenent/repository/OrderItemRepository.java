package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
