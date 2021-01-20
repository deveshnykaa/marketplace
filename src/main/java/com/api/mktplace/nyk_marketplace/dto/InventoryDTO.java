package com.api.mktplace.nyk_marketplace.dto;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class InventoryDTO {
    private int id;
    private String prodId;
    private String varId;
    private String vendorId;
    private String sku;
    private double invQty;
    private double salePrice;

    public InventoryDTO() {
    }

    public InventoryDTO(int id, String prodId, String varId, String vendorId, String sku, double invQty, double salePrice) {
        this.id = id;
        this.prodId = prodId;
        this.varId = varId;
        this.vendorId = vendorId;
        this.sku = sku;
        this.invQty = invQty;
        this.salePrice = salePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getInvQty() {
        return invQty;
    }

    public void setInvQty(double invQty) {
        this.invQty = invQty;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }


}
