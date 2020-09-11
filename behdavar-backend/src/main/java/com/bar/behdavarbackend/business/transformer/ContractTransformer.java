package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.LendingDto;
import com.bar.behdavarbackend.dto.ProductDto;
import com.bar.behdavardatabase.entity.ContractEntity;

import java.util.Optional;

public class ContractTransformer extends BaseAuditorTransformer {

    public static ContractEntity DTO_TO_ENTITY(ContractDto dto, ContractEntity entity) {
        entity.setContractStatus(dto.getContractStatus());
        entity.setContractType(dto.getContractType());

        entity.setSubmitDate(dto.getSubmitDate());
        Optional.ofNullable(dto.getCorporation()).ifPresent(catalogDetailDto -> entity.setCorporation(CatalogDetailTransformer.CREATE_ENTITY_FOR_RELATION(catalogDetailDto.getId())));
        Optional.ofNullable(dto.getLending()).ifPresent(lendingDto -> entity.setLending(LendingTransformer.CREATE_ENTITY_FOR_RELATION(lendingDto.getId())));
        Optional.ofNullable(dto.getProduct()).ifPresent(productDto -> entity.setProduct(ProductTransformer.CREATE_ENTITY_FOR_RELATION(productDto.getId())));

        return entity;
    }

    public static ContractDto ENTITY_TO_DTO(ContractEntity entity, ContractDto dto) {
        transformAuditingFields(entity, dto);
        dto.setContractType(entity.getContractType());
        dto.setContractStatus(entity.getContractStatus());
        dto.setSubmitDate(entity.getSubmitDate());
        Optional.ofNullable(entity.getCorporation()).ifPresent(catalogDetail -> dto.setCorporation(CatalogDetailTransformer.ENTITY_TO_DTO(catalogDetail, new CatalogDetailDto())));
        Optional.ofNullable(entity.getLending()).ifPresent(lending -> dto.setLending(LendingTransformer.ENTITY_TO_DTO(lending, new LendingDto())));
        Optional.ofNullable(entity.getProduct()).ifPresent(product -> dto.setProduct(ProductTransformer.ENTITY_TO_DTO(product, new ProductDto())));

        return dto;
    }

    public static ContractEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        ContractEntity entity = new ContractEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static ContractDto CREATE_DTO_FOR_RELATION(Long id) {
        ContractDto dto = new ContractDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
