package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.PersonEntity;

public class PersonTransformer extends BaseAuditorTransformer {

    public static PersonEntity DTO_TO_ENTITY(PersonDto dto, PersonEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDescription(dto.getDescription());
        entity.setNationalCode(dto.getNationalCode());
        return entity;
    }

    public static PersonDto ENTITY_TO_DTO(PersonEntity entity, PersonDto dto) {
        transformAuditingFields(entity, dto);
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setFullName(entity.getFullName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setDescription(entity.getDescription());
        dto.setNationalCode(entity.getNationalCode());
        return dto;
    }

    public static PersonEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        PersonEntity entity = new PersonEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static PersonDto CREATE_DTO_FOR_RELATION(Long id) {
        PersonDto dto = new PersonDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
