package com.codeonet.product_managenent.service.impl;

import com.codeonet.product_managenent.entity.Inventory;
import com.codeonet.product_managenent.repository.InventoryRepository;
import com.codeonet.product_managenent.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepo.findAll();
    }

    @Override
    public Inventory save(Inventory inventory)  {
        return inventoryRepo.save(inventory);
    }
}