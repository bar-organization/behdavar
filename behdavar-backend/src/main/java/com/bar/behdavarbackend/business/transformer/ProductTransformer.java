package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ProductDto;
import com.bar.behdavardatabase.entity.ProductEntity;

public class ProductTransformer extends BaseAuditorTransformer {

    public static ProductEntity dtoToEntity(ProductDto dto, ProductEntity entity) {
        entity.setProductName(dto.getProductName());
        entity.setProductPlate(dto.getProductPlate());
        entity.setProductShasiNumber(dto.getProductShasiNumber());
        return entity;
    }

    public static ProductDto entityToDto(ProductEntity entity, ProductDto dto) {
        transformAuditingFields(entity, dto);
        dto.setProductName(entity.getProductName());
        dto.setProductPlate(entity.getProductPlate());
        dto.setProductShasiNumber(entity.getProductShasiNumber());
        return dto;
    }

    public static ProductEntity createEntityForRelation(Long id) {
        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
