package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.*;
import com.bar.behdavardatabase.entity.ContractEntity;
import com.bar.behdavardatabase.entity.CustomerEntity;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ContractTransformer extends BaseAuditorTransformer {

    public static ContractEntity dtoToEntity(ContractDto dto, ContractEntity entity) {
        entity.setContractStatus(dto.getContractStatus());
        entity.setContractType(dto.getContractType());

        entity.setSubmitDate(dto.getSubmitDate());
        Optional.ofNullable(dto.getCorporation()).ifPresent(catalogDetailDto -> entity.setCorporation(CatalogDetailTransformer.createEntityForRelation(catalogDetailDto.getId())));
        Optional.ofNullable(dto.getLending()).ifPresent(lendingDto -> entity.setLending(LendingTransformer.createEntityForRelation(lendingDto.getId())));
        Optional.ofNullable(dto.getProduct()).ifPresent(productDto -> entity.setProduct(ProductTransformer.createEntityForRelation(productDto.getId())));

        return entity;
    }

    public static ContractDto entityToDto(ContractEntity entity, ContractDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setContractType(entity.getContractType());
        dto.setContractStatus(entity.getContractStatus());
        dto.setSubmitDate(entity.getSubmitDate());
        Optional.ofNullable(entity.getCorporation()).ifPresent(catalogDetail -> dto.setCorporation(CatalogDetailTransformer.entityToDto(catalogDetail, new CatalogDetailDto())));
        Optional.ofNullable(entity.getLending()).ifPresent(lending -> dto.setLending(LendingTransformer.entityToDto(lending, new LendingDto())));
        Optional.ofNullable(entity.getProduct()).ifPresent(product -> dto.setProduct(ProductTransformer.entityToDto(product, new ProductDto())));

        if (fields.contains(ContractEntity.CUSTOMERS)) {
            Set<CustomerEntity> customers = entity.getCustomers();
            if (!CollectionUtils.isEmpty(customers)) {
                Set<CustomerDto> customerDtos = new HashSet<>();
                customers.forEach(customerEntity -> customerDtos.add(CustomerTransformer.entityToDto(customerEntity, new CustomerDto(), strings)));
                dto.setCustomers(customerDtos);
            }

        }

        return dto;
    }

    public static ContractEntity createEntityForRelation(Long id) {
        ContractEntity entity = new ContractEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static ContractDto createDtoForRelation(Long id) {
        ContractDto dto = new ContractDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
