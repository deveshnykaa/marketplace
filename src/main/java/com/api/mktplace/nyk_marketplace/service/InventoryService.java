package com.api.mktplace.nyk_marketplace.service;

import com.api.mktplace.nyk_marketplace.dao.InventoryRepository;
import com.api.mktplace.nyk_marketplace.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//private static final Logger LOG = LogManager.getLogger(InventoryController.class);
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void updateInventory(List<Inventory> inventories){
        inventories.forEach(inventory -> {
            Optional<Inventory> v= inventoryRepository.findByProdIdAndVarId(inventory.getProdId(), inventory.getVarId());
            if(v.isEmpty()){
                inventory.setSku(inventory.getProdId());
                //inventory.setVendorId(apiKey);
                inventoryRepository.save(inventory);
            }
            else
                inventoryRepository.updateByV2(inventory.getProdId(), inventory.getVarId(), inventory.getInvQty());
        });
    }

    @Transactional
    public void inventoryPriceUpdate(String apiKey, List<Inventory> inventories){
        inventories.forEach(inventory -> {
            Optional<Inventory> v= inventoryRepository.findBySku(inventory.getSku());
            if(v.isEmpty()){
                inventory.setProdId(inventory.getSku());
                inventory.setVendorId(apiKey);
                inventoryRepository.save(inventory);
            }
            else
                inventoryRepository.updateByV3(inventory.getSku(), inventory.getInvQty(), inventory.getSalePrice());
        });
    }
}
