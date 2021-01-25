package com.api.mktplace.nyk_marketplace.consumer;

import com.api.mktplace.nyk_marketplace.config.MessagingConfig;
import com.api.mktplace.nyk_marketplace.dao.CorrelationRepository;
import com.api.mktplace.nyk_marketplace.dto.InventoryStatusDTO;
import com.api.mktplace.nyk_marketplace.entity.Inventory;
import com.api.mktplace.nyk_marketplace.entity.InventoryStatus;
import com.api.mktplace.nyk_marketplace.service.InventoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/*
 List<Inventory> inventories = inventoryStatusDTO.getInventories();
        String status = this.inventoryService.updateInventory(inventoryStatusDTO.getApi_Key(),inventories);
        InventoryStatus inventoryStatus = new InventoryStatus(inventoryStatusDTO.getCorrelationId(),"");
        if(status.equals("Success")){
            inventoryStatus.setStatus(status);
            correlationRepository.save(inventoryStatus);
        }
        else {
            inventoryStatus.setStatus("Failed");
            correlationRepository.save(inventoryStatus);
        }
 */


@Component
public class Middle {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private CorrelationRepository correlationRepository;

    @RabbitListener(queues = MessagingConfig.QUEUE_1)
    public void consumeMessageFromQueue(InventoryStatusDTO inventoryStatusDTO) throws  InterruptedException{
        List<Inventory> inventories = inventoryStatusDTO.getInventories();
        String status;
        String cId = inventoryStatusDTO.getCorrelationId();
        String str = inventories.get(0).getVarId();
        if(str == null || str.length() == 0){
            status = this.inventoryService.inventoryPriceUpdate(inventoryStatusDTO.getApi_Key(),inventories);
        }
        else{
            status = this.inventoryService.updateInventory(inventoryStatusDTO.getApi_Key(),inventories, cId);
        }
        InventoryStatus inventoryStatus = new InventoryStatus(inventoryStatusDTO.getCorrelationId(),"");
        if(status.equals("Success")){
            inventoryStatus.setStatus(status);
            correlationRepository.save(inventoryStatus);
        }
        else {
            inventoryStatus.setStatus("Failed");
            correlationRepository.save(inventoryStatus);
        }
    }
}
