package com.api.mktplace.nyk_marketplace.service;

import com.api.mktplace.nyk_marketplace.dao.CorrelationRepository;
import com.api.mktplace.nyk_marketplace.dao.InventoryRepository;
import com.api.mktplace.nyk_marketplace.dao.ResultRepository;
import com.api.mktplace.nyk_marketplace.dto.V2ReturnDto;
import com.api.mktplace.nyk_marketplace.entity.Inventory;
import com.api.mktplace.nyk_marketplace.entity.InventoryStatus;
import com.api.mktplace.nyk_marketplace.entity.Result;
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

    @Autowired
    private CorrelationRepository correlationRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Transactional
    public String updateInventory(String apiKey, List<Inventory> inventories, String cId){
        int flag=0;
        //List<Storage> storages;
        inventories.forEach(inventory -> {
            Optional<Inventory> v= inventoryRepository.findByProdIdAndVarId(inventory.getProdId(), inventory.getVarId());

            if(v.isEmpty()){
                Result result = new Result();
                result.setPId(inventory.getProdId());
                result.setVId(inventory.getVarId());
                result.setInv(inventory.getInvQty());
                result.setCorrId(cId);
                resultRepository.save(result);
            }
            /*
            if(v.isEmpty()){
                inventory.setSku(inventory.getProdId());
                inventory.setVendorId(apiKey);
                inventory.setSalePrice(1000);
                inventoryRepository.save(inventory);
            }*/
            else
                inventoryRepository.updateByV2(inventory.getProdId(), inventory.getVarId(), inventory.getInvQty());
        });
        return "Success";
    }

    @Transactional
    public String inventoryPriceUpdate(String apiKey, List<Inventory> inventories){
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
        return "Success";
    }

    @Transactional
    public V2ReturnDto getStatusByCorrId(String corrId){
        InventoryStatus inventoryStatus=null;
        InventoryStatus i = correlationRepository.findByCorrelationId(corrId);
        List<Result> result = resultRepository.findByCorrId(corrId);
        V2ReturnDto v2ReturnDto = new V2ReturnDto();
        v2ReturnDto.setFailedProductList(result);
        v2ReturnDto.setStatus(i.getStatus());
        return v2ReturnDto;
    }
}
