package com.api.mktplace.nyk_marketplace.dto;

import com.api.mktplace.nyk_marketplace.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryStatus {

    private List<Inventory> inventories;
    private String status;
    private String message;
}
