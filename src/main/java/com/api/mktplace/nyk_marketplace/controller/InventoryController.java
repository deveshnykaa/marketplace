package com.api.mktplace.nyk_marketplace.controller;

import com.api.mktplace.nyk_marketplace.entity.Seller;
import com.api.mktplace.nyk_marketplace.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PutMapping("/updateInventory")
    public void updateInven(@RequestBody List<Seller> seller){
        this.inventoryService.updateInven(seller);
    }
}
