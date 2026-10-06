package com.codeonet.product_managenent.service;

import com.codeonet.product_managenent.entity.Inventory;

import java.util.List;

public interface InventoryService {

    List<Inventory> getAllInventory();

    Inventory save(Inventory inventory);

}