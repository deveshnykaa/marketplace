package com.api.mktplace.nyk_marketplace.controller;

import com.api.mktplace.nyk_marketplace.config.MessagingConfig;
import com.api.mktplace.nyk_marketplace.converter.InventoryConverter;
import com.api.mktplace.nyk_marketplace.dto.InventoryDTO;
import com.api.mktplace.nyk_marketplace.entity.Inventory;
import com.api.mktplace.nyk_marketplace.service.InventoryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryConverter inventoryConverter;
    //private static final Logger LOG = LogManager.getLogger(InventoryController.class);

    @PutMapping("/v2/updateInventory")
    public String updateInventory(@RequestHeader(value = "Authorization") String apiKey , @RequestBody List<InventoryDTO> inventoryDTOS){
        //LOG.info("UpdateInventory Request sent with RequestBody "+vendors);
        List<Inventory> inventories = inventoryConverter.dtoToEntity(inventoryDTOS);

        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY_1, inventories);
        //template.convertSendAndReceive()
        // template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY_1, apiKey, inventories);
        return "Order taken!!";
        //this.inventoryService.updateInventory(apiKey, inventories);
    }

    @PutMapping("/v3/inventoryPriceUpdate")
    public void inventoryPriceUpdate(@RequestHeader(value = "Authorization") String apiKey , @RequestBody List<InventoryDTO> inventoryDTOS){
        //LOG.info("InventoryPriceUpdate Request sent with RequestBody "+sellers);
        List<Inventory> inventories = inventoryConverter.dtoToEntity(inventoryDTOS);
        this.inventoryService.inventoryPriceUpdate(apiKey, inventories);
    }
}
