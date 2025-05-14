package com.ntosh.purchaseorders.mapper;

import com.ntosh.purchaseorders.dto.PurchaseOrderDTO;
import com.ntosh.purchaseorders.entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);

    PurchaseOrderDTO toPurchaseOrderDTO(PurchaseOrder purchaseOrder);
    PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO);
}
