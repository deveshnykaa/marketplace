package com.api.mktplace.nyk_marketplace.service;

import com.api.mktplace.nyk_marketplace.dao.InventoryRepository;
import com.api.mktplace.nyk_marketplace.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void updateInven(List<Seller> seller){
        seller.forEach(seller1 -> {
            seller1 .getSellerId(); // Authorisation Part to be implemented later for vendor_id
            seller1.getProductId(); // Authorisation Part to be implemented later for product_id
            inventoryRepository.updateByPID(seller1.getProductId(), seller1.getPrice(), seller1.getQuantity());
        });

    }
}
