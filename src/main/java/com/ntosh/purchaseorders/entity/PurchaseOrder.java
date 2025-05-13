package com.ntosh.purchaseorders.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "purchase_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String purchaseOrderNumber;
        private String supplierName;
        private Double amount;
        private String status;
        private String createdBy;
    }
