package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.AddressDto;
import com.bar.behdavardatabase.entity.AddressEntity;

import java.util.Optional;

public class AddressTransformer {

    public static AddressEntity DTO_TO_ENTITY(AddressDto dto, AddressEntity entity) {
        entity.setDescription(dto.getDescription());
        entity.setGeoDivision(GeoDivisionTransformer.CREATE_ENTITY_FOR_RELATION(dto.getGeoDivision().getId()));
        entity.setMainAlley(dto.getMainAlley());
        entity.setMainStreet(dto.getMainStreet());
        Optional.ofNullable(dto.getPerson()).ifPresent(personDto -> entity.setPerson(PersonTransformer.CREATE_ENTITY_FOR_RELATION(personDto.getId())));
        entity.setPlate(dto.getPlate());
        entity.setPostalCode(dto.getPostalCode());
        entity.setSubAlley(dto.getSubAlley());
        entity.setSubStreet(dto.getSubStreet());
        return entity;
    }

    public static AddressDto ENTITY_TO_DTO(AddressEntity entity, AddressDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        //TODO add required fields
        return dto;
    }

    public static AddressEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        AddressEntity entity = new AddressEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
