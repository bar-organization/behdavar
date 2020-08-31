package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.GuarantorDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.GuarantorEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GuarantorTransformer extends BaseAuditorTransformer {

    public static GuarantorEntity DTO_TO_ENTITY(GuarantorDto dto, GuarantorEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setPerson(PersonTransformer.CREATE_ENTITY_FOR_RELATION(dto.getPerson().getId()));
        entity.setRelationType(dto.getRelationType());
        return entity;
    }

    public static GuarantorDto ENTITY_TO_DTO(GuarantorEntity entity, GuarantorDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setId(entity.getId());
        dto.setRelationType(entity.getRelationType());

        if (fields.contains(GuarantorEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.ENTITY_TO_DTO(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.CREATE_DTO_FOR_RELATION(entity.getContract().getId()));
        }

        if (fields.contains(GuarantorEntity.PERSON)) {
            dto.setPerson(PersonTransformer.ENTITY_TO_DTO(entity.getPerson(), new PersonDto()));
        } else {
            dto.setPerson(PersonTransformer.CREATE_DTO_FOR_RELATION(entity.getPerson().getId()));
        }

        return dto;
    }

    public static GuarantorEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        GuarantorEntity entity = new GuarantorEntity();
        entity.setId(id);
        return entity;
    }
}
