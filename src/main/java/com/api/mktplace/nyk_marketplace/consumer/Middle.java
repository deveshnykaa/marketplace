package com.api.mktplace.nyk_marketplace.consumer;

import com.api.mktplace.nyk_marketplace.config.MessagingConfig;
import com.api.mktplace.nyk_marketplace.entity.Inventory;
import com.api.mktplace.nyk_marketplace.service.InventoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Middle {

    @Autowired
    private InventoryService inventoryService;

    @RabbitListener(queues = MessagingConfig.QUEUE_1)
    public void consumeMessageFromQueue(List<Inventory> inventories){
        this.inventoryService.updateInventory(inventories);
    }
}
