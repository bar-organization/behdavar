package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.CustomerEntity;

public class CustomerTransformer {

    public static CustomerEntity DTO_TO_ENTITY(CustomerDto dto, CustomerEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setPerson(PersonTransformer.CREATE_ENTITY_FOR_RELATION(dto.getPerson().getId()));
        return entity;
    }

    public static CustomerDto ENTITY_TO_DTO(CustomerEntity entity, CustomerDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setContract(ContractTransformer.ENTITY_TO_DTO(entity.getContract(), new ContractDto()));
        dto.setPerson(PersonTransformer.ENTITY_TO_DTO(entity.getPerson(), new PersonDto()));
        return dto;
    }

    public static CustomerEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(id);
        return entity;
    }
}
