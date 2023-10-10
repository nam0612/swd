//package com.fpt.swd.database.dto;
//
//import com.ecommerce.enums.CatalogStatus;
//import com.ecommerce.enums.ProductType;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@Builder
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class ProductDTO implements Serializable {
//    private String productId;
//
//    private String productCode;
//
//    private String productName;
//
//    private String description;
//
//    private String imageUrl;
//
//    private Boolean checkSerial;
//
//    private ProductType productType;
//
//    private String unit;
//
//    private Integer vat;
//
//    private Long quantityReq;
//
//    private Double price;
//
//    private CatalogStatus status;
//
//    private String createdUser;
//
//    private Date createdDate;
//
//    private String updatedUser;
//
//    private Date updatedDate;
//
//    private List<ProductPriceDTO> lstPrice;
//
//    private String importMessage;
//
//    private boolean isError;
//}
