package com.ntosh.purchaseorders.dto;

import lombok.Data;

@Data
public class PurchaseOrderDTO {

    private Long id;
    private String purchaseOrderNumber;
    private String supplierName;
    private Double amount;
    private String status;
    private String createdBy;
}
