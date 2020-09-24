package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.GuarantorDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.GuarantorEntity;
import com.bar.behdavardatabase.entity.PersonEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GuarantorTransformer extends BaseAuditorTransformer {

    public static GuarantorEntity dtoToEntity(GuarantorDto dto, GuarantorEntity entity) {
        entity.setContract(ContractTransformer.createEntityForRelation(dto.getContract().getId()));
        entity.setPerson(PersonTransformer.createEntityForRelation(dto.getPerson().getId()));
        entity.setRelationType(dto.getRelationType());
        return entity;
    }

    public static GuarantorDto entityToDto(GuarantorEntity entity, GuarantorDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setRelationType(entity.getRelationType());

        if (fields.contains(GuarantorEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.entityToDto(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.createDtoForRelation(entity.getContract().getId()));
        }

        if (fields.contains(GuarantorEntity.PERSON)) {
            dto.setPerson(PersonTransformer.entityToDto(entity.getPerson(), new PersonDto(),
                    fields.contains(PersonEntity.CONTACTS) ? PersonEntity.CONTACTS : null));
        } else {
            dto.setPerson(PersonTransformer.createDtoForRelation(entity.getPerson().getId()));
        }

        return dto;
    }

    public static GuarantorEntity createEntityForRelation(Long id) {
        GuarantorEntity entity = new GuarantorEntity();
        entity.setId(id);
        return entity;
    }
}
