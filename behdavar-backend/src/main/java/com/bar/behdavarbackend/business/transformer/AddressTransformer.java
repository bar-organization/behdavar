package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.AddressDto;
import com.bar.behdavardatabase.entity.AddressEntity;

import java.util.Optional;

public class AddressTransformer extends BaseAuditorTransformer {

    public static AddressEntity dtoToEntity(AddressDto dto, AddressEntity entity) {
        entity.setDescription(dto.getDescription());
        entity.setGeoDivision(GeoDivisionTransformer.createEntityForRelation(dto.getGeoDivision().getId()));
        entity.setMainAlley(dto.getMainAlley());
        entity.setMainStreet(dto.getMainStreet());
        Optional.ofNullable(dto.getPerson()).ifPresent(personDto -> entity.setPerson(PersonTransformer.createEntityForRelation(personDto.getId())));
        entity.setPlate(dto.getPlate());
        entity.setPostalCode(dto.getPostalCode());
        entity.setSubAlley(dto.getSubAlley());
        entity.setSubStreet(dto.getSubStreet());
        return entity;
    }

    public static AddressDto entityToDto(AddressEntity entity, AddressDto dto) {
        transformAuditingFields(entity, dto);
        //TODO add required fields
        return dto;
    }

    public static AddressEntity createEntityForRelation(Long id) {
        AddressEntity entity = new AddressEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
