package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.GeoDivisionDto;
import com.bar.behdavardatabase.entity.GeoDivisionEntity;

import java.util.Optional;

public class GeoDivisionTransformer {

    public static GeoDivisionEntity DTO_TO_ENTITY(GeoDivisionDto dto, GeoDivisionEntity entity) {
        entity.setCode(dto.getCode());
        entity.setGeoDivisionType(dto.getGeoDivisionType());
        entity.setName(entity.getName());
        Optional.ofNullable(dto.getParent())
                .ifPresent(divisionDto -> entity.setParent(CREATE_ENTITY_FOR_RELATION(dto.getParent().getId())));
        return entity;
    }

    public static GeoDivisionEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        GeoDivisionEntity entity = new GeoDivisionEntity();
        entity.setId(id);
        return entity;
    }
}
