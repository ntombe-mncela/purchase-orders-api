package com.ntosh.purchaseorders.repository;


import com.ntosh.purchaseorders.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
