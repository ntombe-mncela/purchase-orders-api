package com.ntosh.purchaseorders.controller;

import com.ntosh.purchaseorders.dto.PurchaseOrderDTO;
import com.ntosh.purchaseorders.entity.PurchaseOrder;
import com.ntosh.purchaseorders.mapper.PurchaseOrderMapper;
import com.ntosh.purchaseorders.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {


    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper mapper;

    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapper mapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.mapper = mapper;
    }

    @PostMapping
    public PurchaseOrderDTO createPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrder) {
        PurchaseOrder po = mapper.toEntity(purchaseOrder);
        return mapper.toPurchaseOrderDTO(po);
    }

    @GetMapping
    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll().stream()
                .map(mapper::toPurchaseOrderDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
        public ResponseEntity<PurchaseOrderDTO> getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderRepository.findById(id)
                .map(po -> ResponseEntity.ok(mapper.toPurchaseOrderDTO(po)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> update(@PathVariable Long id, @RequestBody PurchaseOrderDTO updatedDTO) {
        return purchaseOrderRepository.findById(id).map(existing -> {
            existing.setAmount(updatedDTO.getAmount());
            existing.setSupplierName(updatedDTO.getSupplierName());
            existing.setStatus(updatedDTO.getStatus());
            existing.setCreatedBy(updatedDTO.getCreatedBy());
            return ResponseEntity.ok(mapper.toPurchaseOrderDTO(purchaseOrderRepository.save(existing)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PurchaseOrder> deletePurchaseOrderById(@PathVariable Long id) {
        purchaseOrderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
