package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem, Long> {
}
