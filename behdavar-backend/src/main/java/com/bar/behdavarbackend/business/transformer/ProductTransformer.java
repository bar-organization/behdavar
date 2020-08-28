package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ProductDto;
import com.bar.behdavardatabase.entity.ProductEntity;

public class ProductTransformer {

    public static ProductEntity DTO_TO_ENTITY(ProductDto dto, ProductEntity entity) {
        entity.setProductName(dto.getProductName());
        entity.setProductPlate(dto.getProductPlate());
        entity.setProductShasiNumber(dto.getProductShasiNumber());
        return entity;
    }

    public static ProductDto ENTITY_TO_DTO(ProductEntity entity, ProductDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setProductName(entity.getProductName());
        dto.setProductPlate(entity.getProductPlate());
        dto.setProductShasiNumber(entity.getProductShasiNumber());
        return dto;
    }

    public static ProductEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
