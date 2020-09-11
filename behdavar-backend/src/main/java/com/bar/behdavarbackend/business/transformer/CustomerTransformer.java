package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.CustomerEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerTransformer extends BaseAuditorTransformer {

    public static CustomerEntity dtoToEntity(CustomerDto dto, CustomerEntity entity) {
        entity.setContract(ContractTransformer.createEntityForRelation(dto.getContract().getId()));
        entity.setPerson(PersonTransformer.createEntityForRelation(dto.getPerson().getId()));
        return entity;
    }

    public static CustomerDto entityToDto(CustomerEntity entity, CustomerDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        if (fields.contains(CustomerEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.entityToDto(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.createDtoForRelation(entity.getContract().getId()));
        }
        if (fields.contains(CustomerEntity.PERSON)) {
            dto.setPerson(PersonTransformer.entityToDto(entity.getPerson(), new PersonDto()));
        } else {
            dto.setPerson(PersonTransformer.createDtoForRelation(entity.getPerson().getId()));
        }
        return dto;
    }

    public static CustomerEntity createEntityForRelation(Long id) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(id);
        return entity;
    }
}
