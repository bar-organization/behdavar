package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.CatalogDto;
import com.bar.behdavardatabase.entity.CatalogEntity;

public class CatalogTransformer {

    public static CatalogEntity DTO_TO_ENTITY(CatalogDto dto, CatalogEntity entity) {
        entity.setCode(dto.getCode());
        entity.setEnglishTitle(dto.getEnglishTitle());
        entity.setActive(entity.getActive());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
