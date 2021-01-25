package com.api.mktplace.nyk_marketplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Result {
    @JsonIgnore
    @Id
    private int id;

    private String pId;
    private String vId;
    private double inv;

    @JsonIgnore
    private String corrId;
}
