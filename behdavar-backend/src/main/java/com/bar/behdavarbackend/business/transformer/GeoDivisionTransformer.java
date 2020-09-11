package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.GeoDivisionDto;
import com.bar.behdavardatabase.entity.GeoDivisionEntity;

import java.util.Optional;

public class GeoDivisionTransformer extends BaseAuditorTransformer {

    public static GeoDivisionEntity dtoToEntity(GeoDivisionDto dto, GeoDivisionEntity entity) {
        entity.setCode(dto.getCode());
        entity.setGeoDivisionType(dto.getGeoDivisionType());
        entity.setName(dto.getName());
        Optional.ofNullable(dto.getParent())
                .ifPresent(divisionDto -> entity.setParent(createEntityForRelation(dto.getParent().getId())));
        return entity;
    }

    public static GeoDivisionDto entityToDto(GeoDivisionEntity entity, GeoDivisionDto dto) {
        transformAuditingFields(entity, dto);
        dto.setCode(entity.getCode());
        dto.setGeoDivisionType(entity.getGeoDivisionType());
        dto.setName(entity.getName());
        Optional.ofNullable(entity.getParent())
                .ifPresent(divisionDto -> dto.setParent(createDtoForRelation(entity.getParent().getId())));
        return dto;
    }


    public static GeoDivisionEntity createEntityForRelation(Long id) {
        GeoDivisionEntity entity = new GeoDivisionEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static GeoDivisionDto createDtoForRelation(Long id) {
        GeoDivisionDto dto = new GeoDivisionDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
