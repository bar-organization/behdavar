package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.GuarantorDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.GuarantorEntity;

public class GuarantorTransformer {

    public static GuarantorEntity DTO_TO_ENTITY(GuarantorDto dto, GuarantorEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setPerson(PersonTransformer.CREATE_ENTITY_FOR_RELATION(dto.getPerson().getId()));
        entity.setRelationType(dto.getRelationType());
        return entity;
    }

    public static GuarantorDto ENTITY_TO_DTO(GuarantorEntity entity, GuarantorDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setContract(ContractTransformer.ENTITY_TO_DTO(entity.getContract(), new ContractDto()));
        dto.setPerson(PersonTransformer.ENTITY_TO_DTO(entity.getPerson(), new PersonDto()));
        dto.setRelationType(entity.getRelationType());
        return dto;
    }

    public static GuarantorEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        GuarantorEntity entity = new GuarantorEntity();
        entity.setId(id);
        return entity;
    }
}
