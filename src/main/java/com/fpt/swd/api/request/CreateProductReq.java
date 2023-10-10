package com.fpt.swd.api.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Data
public class CreateProductReq implements Serializable {
    private String productCode;
    private String productName;
    private String description;
    private String imageUrl;
    private Boolean checkSerial;
    private String productType;
    private String unit;
    private Integer vat;
}
