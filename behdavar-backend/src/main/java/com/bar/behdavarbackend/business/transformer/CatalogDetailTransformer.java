package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavarbackend.dto.CatalogDto;
import com.bar.behdavardatabase.entity.CatalogDetailEntity;

public class CatalogDetailTransformer extends BaseAuditorTransformer {

    public static CatalogDetailEntity dtoToEntity(CatalogDetailDto dto, CatalogDetailEntity entity) {
        entity.setCode(dto.getCode());
        entity.setEnglishTitle(dto.getEnglishTitle());
        entity.setActive(dto.getActive());
        entity.setTitle(dto.getTitle());
        entity.setCatalog(CatalogTransformer.createEntityForRelation(dto.getCatalog().getId()));
        return entity;
    }

    public static CatalogDetailDto entityToDto(CatalogDetailEntity entity, CatalogDetailDto dto) {
        transformAuditingFields(entity, dto);
        dto.setCode(entity.getCode());
        dto.setEnglishTitle(entity.getEnglishTitle());
        dto.setActive(entity.getActive());
        dto.setTitle(entity.getTitle());
        dto.setCatalog(CatalogTransformer.entityToDto(entity.getCatalog(), new CatalogDto()));
        return dto;
    }

    public static CatalogDetailEntity createEntityForRelation(Long id) {
        CatalogDetailEntity entity = new CatalogDetailEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
