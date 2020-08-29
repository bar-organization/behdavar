package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.PersonEntity;

public class PersonTransformer {

    public static PersonEntity DTO_TO_ENTITY(PersonDto dto, PersonEntity entity) {
        return entity;
    }

    public static PersonDto ENTITY_TO_DTO(PersonEntity entity, PersonDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
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
