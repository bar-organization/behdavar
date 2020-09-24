package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContactDto;
import com.bar.behdavardatabase.entity.ContactEntity;

public class ContactTransformer extends BaseAuditorTransformer {

    public static ContactEntity dtoToEntity(ContactDto dto, ContactEntity entity) {
        entity.setPerson(PersonTransformer.createEntityForRelation(dto.getPerson().getId()));
        entity.setPhoneType(dto.getPhoneType());
        entity.setNumber(dto.getNumber());
        entity.setConfirmed(dto.getConfirmed());
        entity.setPreCode(dto.getPreCode());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    public static ContactDto entityToDto(ContactEntity entity, ContactDto dto, String... field) {
        transformAuditingFields(entity, dto);
        dto.setPerson(PersonTransformer.createDtoForRelation(entity.getPerson().getId()));
        dto.setPhoneType(entity.getPhoneType());
        dto.setNumber(entity.getNumber());
        dto.setConfirmed(entity.getConfirmed());
        dto.setPreCode(entity.getPreCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static ContactEntity createEntityForRelation(Long id) {
        ContactEntity entity = new ContactEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static ContactDto createDtoForRelation(Long id) {
        ContactDto dto = new ContactDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
