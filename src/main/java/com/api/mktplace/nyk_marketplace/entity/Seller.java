package com.api.mktplace.nyk_marketplace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seller {
    @Id
    private int productId;
    //@Column(name = "Seller_Id")
    private Long sellerId;
    //@Column(name = "Quantity")
    private  String quantity;
    //@Column(name = "Price")
    private String price;

    @Override
    public String toString() {
        return "Seller{" +
                "productId=" + productId +
                ", sellerId=" + sellerId +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public Seller() {
    }

    public Seller(int productId, Long sellerId, String quantity, String price) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
