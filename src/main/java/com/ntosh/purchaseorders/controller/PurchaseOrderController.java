package com.ntosh.purchaseorders.controller;

import com.ntosh.purchaseorders.entity.PurchaseOrder;
import com.ntosh.purchaseorders.repository.PurchaseOrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @PostMapping
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @GetMapping("/{id}")
        public PurchaseOrder getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> update(@PathVariable Long id, @RequestBody PurchaseOrder updated) {
        return purchaseOrderRepository.findById(id).map(existing -> {
            existing.setAmount(updated.getAmount());
            existing.setSupplierName(updated.getSupplierName());
            existing.setStatus(updated.getStatus());
            existing.setCreatedBy(updated.getCreatedBy());
            return ResponseEntity.ok(purchaseOrderRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PurchaseOrder> deletePurchaseOrderById(@PathVariable Long id) {
        purchaseOrderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
