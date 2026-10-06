package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.entity.Vendor;
import com.codeonet.product_managenent.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService service;

    @PostMapping("/add")
    public Vendor addVendor(@RequestBody Vendor vendor){
        return service.addVendorDetail(vendor);
    }

    @GetMapping("/getAll")
    public List<Vendor> getAllVendors(){
        return service.getAllVendors();
    }

    @GetMapping("/search/{name}")
    public List<Vendor> getVendorByName(@PathVariable String name){
        return service.getVendorByName(name);
    }
    @DeleteMapping("/deleteByName/{name}")
    public String deleteVendorByItsName(@PathVariable String name){
        service.deleteByName(name);
        return "Deleted";
    }
}
