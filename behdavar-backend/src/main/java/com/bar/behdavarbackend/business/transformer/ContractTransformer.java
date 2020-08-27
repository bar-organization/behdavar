package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavardatabase.entity.ContractEntity;

public class ContractTransformer {

    public static ContractEntity DTO_TO_ENTITY(ContractDto dto, ContractEntity entity) {

        return entity;
    }

    public static ContractDto ENTITY_TO_DTO(ContractEntity entity, ContractDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        return dto;
    }

    public static ContractEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        ContractEntity entity = new ContractEntity();
        entity.setId(id);
        return entity;
    }
}
