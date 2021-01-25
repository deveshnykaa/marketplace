package com.api.mktplace.nyk_marketplace.controller;

import com.api.mktplace.nyk_marketplace.config.MessagingConfig;
import com.api.mktplace.nyk_marketplace.converter.InventoryConverter;
import com.api.mktplace.nyk_marketplace.dto.InventoryDTO;
import com.api.mktplace.nyk_marketplace.dto.InventoryStatusDTO;
import com.api.mktplace.nyk_marketplace.dto.V2ReturnDto;
import com.api.mktplace.nyk_marketplace.entity.Inventory;
import com.api.mktplace.nyk_marketplace.entity.Result;
import com.api.mktplace.nyk_marketplace.service.InventoryService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@Log4j2
@RestController
public class InventoryController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryConverter inventoryConverter;
    //private static final Logger LOG = LogManager.getLogger(InventoryController.class)
    Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @GetMapping("/getStatus/{corrId}")
    public V2ReturnDto getStatus(@PathVariable("corrId") String corrId){
        return inventoryService.getStatusByCorrId(corrId);
    }

    @PutMapping("/v2/updateInventory")
    public String updateInventory(@RequestHeader(value = "Authorization") String apiKey , @RequestBody List<InventoryDTO> inventoryDTOS){
        //LOG.info("UpdateInventory Request sent with RequestBody "+vendors);
        List<Inventory> inventories = inventoryConverter.dtoToEntity(inventoryDTOS);
        UUID correlationId = UUID.randomUUID();
        InventoryStatusDTO inventoryStatusDTO = new InventoryStatusDTO(inventories,correlationId.toString(),"",apiKey);
        logger.info(correlationId.toString(), inventoryStatusDTO.getInventories());
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY_1, inventoryStatusDTO);
        //template.convertSendAndReceiveAsType(MessagingConfig.ROUTING_KEY_1, inventories, );
        // template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY_1, apiKey, inventories);
        return ("Request Accepted and generated Correlation Id : "+correlationId);
    }

    @PutMapping("/v3/inventoryPriceUpdate")
    public String inventoryPriceUpdate(@RequestHeader(value = "Authorization") String apiKey , @RequestBody List<InventoryDTO> inventoryDTOS){
        //LOG.info("InventoryPriceUpdate Request sent with RequestBody "+sellers);
        List<Inventory> inventories = inventoryConverter.dtoToEntity(inventoryDTOS);
        UUID correlationId = UUID.randomUUID();
        InventoryStatusDTO inventoryStatusDTO = new InventoryStatusDTO(inventories,correlationId.toString(),"",apiKey);
        logger.info(correlationId.toString(), inventoryStatusDTO.getInventories());
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY_1, inventoryStatusDTO);
        //template.convertSendAndReceiveAsType(MessagingConfig.ROUTING_KEY_1, inventories, );
        // template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY_1, apiKey, inventories);
        return ("Request Accepted and generated Correlation Id : "+correlationId);
    }
}
