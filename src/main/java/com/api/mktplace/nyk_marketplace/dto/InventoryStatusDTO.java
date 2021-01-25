package com.api.mktplace.nyk_marketplace.dto;

import com.api.mktplace.nyk_marketplace.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryStatusDTO {

    private List<Inventory> inventories;

    @Id
    private String correlationId;

    private String status;
    private String api_Key;
}
