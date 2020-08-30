package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavarbackend.dto.CatalogDto;
import com.bar.behdavardatabase.entity.CatalogDetailEntity;

public class CatalogDetailTransformer {

    public static CatalogDetailEntity DTO_TO_ENTITY(CatalogDetailDto dto, CatalogDetailEntity entity) {
        entity.setCode(dto.getCode());
        entity.setEnglishTitle(dto.getEnglishTitle());
        entity.setActive(dto.getActive());
        entity.setTitle(dto.getTitle());
        entity.setCatalog(CatalogTransformer.CREATE_ENTITY_FOR_RELATION(dto.getCatalog().getId()));
        return entity;
    }

    public static CatalogDetailDto ENTITY_TO_DTO(CatalogDetailEntity entity, CatalogDetailDto dto) {
        dto.setCode(entity.getCode());
        dto.setEnglishTitle(entity.getEnglishTitle());
        dto.setActive(entity.getActive());
        dto.setTitle(entity.getTitle());
        dto.setCatalog(CatalogTransformer.ENTITY_TO_DTO(entity.getCatalog(), new CatalogDto()));
        return dto;
    }

    public static CatalogDetailEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        CatalogDetailEntity entity = new CatalogDetailEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
