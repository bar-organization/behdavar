package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.GeoDivisionDto;
import com.bar.behdavardatabase.entity.GeoDivisionEntity;

import java.util.Optional;

public class GeoDivisionTransformer {

    public static GeoDivisionEntity DTO_TO_ENTITY(GeoDivisionDto dto, GeoDivisionEntity entity) {
        entity.setCode(dto.getCode());
        entity.setGeoDivisionType(dto.getGeoDivisionType());
        entity.setName(dto.getName());
        Optional.ofNullable(dto.getParent())
                .ifPresent(divisionDto -> entity.setParent(CREATE_ENTITY_FOR_RELATION(dto.getParent().getId())));
        return entity;
    }

    public static GeoDivisionDto ENTITY_TO_DTO(GeoDivisionEntity entity, GeoDivisionDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setCode(entity.getCode());
        dto.setGeoDivisionType(entity.getGeoDivisionType());
        dto.setName(entity.getName());
        Optional.ofNullable(entity.getParent())
                .ifPresent(divisionDto -> dto.setParent(CREATE_DTO_FOR_RELATION(entity.getParent().getId())));
        return dto;
    }


    public static GeoDivisionEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        GeoDivisionEntity entity = new GeoDivisionEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static GeoDivisionDto CREATE_DTO_FOR_RELATION(Long id) {
        GeoDivisionDto dto = new GeoDivisionDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
