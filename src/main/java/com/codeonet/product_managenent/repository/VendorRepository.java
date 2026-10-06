package com.codeonet.product_managenent.repository;

import com.codeonet.product_managenent.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

   List<Vendor> findByName(String name);
   void deleteByName(String name);
}
