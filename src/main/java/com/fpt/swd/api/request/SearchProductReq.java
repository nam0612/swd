package com.fpt.swd.api.request;

import lombok.Data;

@Data
public class SearchProductReq {
    private String productCode;
    private String productName;
//    private ProductType productType;
//    private CatalogStatus status;
    private String search;
}
