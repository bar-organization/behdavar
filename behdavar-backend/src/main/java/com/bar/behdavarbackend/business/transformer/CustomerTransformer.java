package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.CustomerEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerTransformer {

    public static CustomerEntity DTO_TO_ENTITY(CustomerDto dto, CustomerEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setPerson(PersonTransformer.CREATE_ENTITY_FOR_RELATION(dto.getPerson().getId()));
        return entity;
    }

    public static CustomerDto ENTITY_TO_DTO(CustomerEntity entity, CustomerDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        if (fields.contains(CustomerEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.ENTITY_TO_DTO(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.CREATE_DTO_FOR_RELATION(entity.getContract().getId()));
        }
        if (fields.contains(CustomerEntity.PERSON)) {
            dto.setPerson(PersonTransformer.ENTITY_TO_DTO(entity.getPerson(), new PersonDto()));
        } else {
            dto.setPerson(PersonTransformer.CREATE_DTO_FOR_RELATION(entity.getPerson().getId()));
        }
        return dto;
    }

    public static CustomerEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(id);
        return entity;
    }
}
