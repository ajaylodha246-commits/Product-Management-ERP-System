package com.codeonet.product_managenent.controller;

import com.codeonet.product_managenent.entity.Inventory;
import com.codeonet.product_managenent.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getAll")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PostMapping("/add")
    public Inventory saveInventory(@RequestBody Inventory inventory) {
        return inventoryService.save(inventory);
    }
}