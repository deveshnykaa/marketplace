package com.api.mktplace.nyk_marketplace.converter;

import com.api.mktplace.nyk_marketplace.dto.InventoryDTO;
import com.api.mktplace.nyk_marketplace.entity.Inventory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryConverter {
    public InventoryDTO entityToDto(Inventory inventory){
        InventoryDTO dto = new InventoryDTO();
        dto.setId(inventory.getId());
        dto.setProdId(inventory.getProdId());
        dto.setVarId(inventory.getVarId());
        dto.setInvQty(inventory.getInvQty());
        dto.setSku(inventory.getSku());
        dto.setSalePrice(inventory.getSalePrice());
        dto.setVendorId(inventory.getVendorId());
        return dto;
    }

    public Inventory dtoToEntity(InventoryDTO inventoryDTO){
        Inventory inv = new Inventory();
        inv.setId(inventoryDTO.getId());
        inv.setProdId(inventoryDTO.getProdId());
        inv.setVarId(inventoryDTO.getVarId());
        inv.setInvQty(inventoryDTO.getInvQty());
        inv.setSku(inventoryDTO.getSku());
        inv.setSalePrice(inventoryDTO.getSalePrice());
        inv.setVendorId(inventoryDTO.getVendorId());
        return inv;
    }

    public List<InventoryDTO> entityToDto(List<Inventory> inventories){
        return inventories.stream().map(inventory -> entityToDto(inventory)).collect(Collectors.toList());
    }

    public List<Inventory> dtoToEntity(List<InventoryDTO> inventoryDTOS){
        return inventoryDTOS.stream().map(inventoryDTO -> dtoToEntity(inventoryDTO)).collect(Collectors.toList());
    }
}
