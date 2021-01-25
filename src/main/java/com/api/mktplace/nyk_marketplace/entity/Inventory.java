package com.api.mktplace.nyk_marketplace.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory", uniqueConstraints={@UniqueConstraint(name="uc_apiKey_prodId_varId_vendorId",  columnNames={"vendor_id","prod_id","var_id"})})
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "prod_id")
    private String prodId;

    @Column(name = "var_id")
    private String varId;

    @Column(name = "vendor_id")
    private String vendorId;

    private String sku;
    private double invQty;
    private double salePrice;



    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", prodId='" + prodId + '\'' +
                ", varId='" + varId + '\'' +
                ", vendorId='" + vendorId + '\'' +
                ", sku='" + sku + '\'' +
                ", invQty=" + invQty +
                ", salePrice=" + salePrice +
                '}';
    }
}