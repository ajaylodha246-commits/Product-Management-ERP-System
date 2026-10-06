package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.entity.Vendor;

import java.util.List;

public interface VendorService {

    public List<Vendor> getAllVendors();
    public Vendor addVendorDetail(Vendor vendor);
    public List<Vendor>  getVendorByName(String name);
    public void deleteByName(String name);
}
