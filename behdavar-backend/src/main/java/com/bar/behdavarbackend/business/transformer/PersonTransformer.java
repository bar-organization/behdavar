package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContactDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.PersonEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonTransformer extends BaseAuditorTransformer {

    public static PersonEntity dtoToEntity(PersonDto dto, PersonEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setFullName(dto.getFullName());
        entity.setFatherName(dto.getFatherName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDescription(dto.getDescription());
        entity.setNationalCode(dto.getNationalCode());
        return entity;
    }

    public static PersonDto entityToDto(PersonEntity entity, PersonDto dto, String... field) {
        List<String> fields = Arrays.stream(field).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setFullName(entity.getFullName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setDescription(entity.getDescription());
        dto.setNationalCode(entity.getNationalCode());
        dto.setFatherName(entity.getFatherName());
        if (fields.contains(PersonEntity.CONTACTS) && !CollectionUtils.isEmpty(entity.getContacts())) {
            List<ContactDto> contactDtos = new ArrayList<>();
            entity.getContacts().forEach(contactEntity -> contactDtos.add(ContactTransformer.entityToDto(contactEntity, new ContactDto())));
            dto.setContacts(contactDtos);

        }
        return dto;
    }

    public static PersonEntity createEntityForRelation(Long id) {
        PersonEntity entity = new PersonEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static PersonDto createDtoForRelation(Long id) {
        PersonDto dto = new PersonDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
