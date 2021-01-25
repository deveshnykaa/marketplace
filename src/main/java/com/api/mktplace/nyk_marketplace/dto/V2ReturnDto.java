package com.api.mktplace.nyk_marketplace.dto;

import com.api.mktplace.nyk_marketplace.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class V2ReturnDto {
    private String Status;
    private List<Result> failedProductList;
}
