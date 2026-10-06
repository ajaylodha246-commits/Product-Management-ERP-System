package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.entity.Vendor;
import com.codeonet.product_managenent.repository.VendorRepository;
import com.codeonet.product_managenent.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {


    @Autowired
    private VendorRepository vendorRepo;


    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }

    @Override
    public List<Vendor> getVendorByName(String name) {
        return vendorRepo.findByName(name);
    }

     @Override
     public Vendor addVendorDetail(Vendor vendor) {
        return vendorRepo.save(vendor);
    }

    public void deleteByName(String name) {
         vendorRepo.deleteByName(name);
    }
}
