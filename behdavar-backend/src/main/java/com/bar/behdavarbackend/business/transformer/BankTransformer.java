package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.AddressDto;
import com.bar.behdavarbackend.dto.BankDto;
import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavardatabase.entity.BankBranchEntity;

import java.util.Optional;

public class BankTransformer extends BaseAuditorTransformer {

    public static BankBranchEntity DTO_TO_ENTITY(BankDto dto, BankBranchEntity entity) {
        entity.setCode(dto.getCode());
        Optional.ofNullable(dto.getAddress()).ifPresent(addressDto -> entity.setAddress(AddressTransformer.CREATE_ENTITY_FOR_RELATION(addressDto.getId())));
        entity.setBankType(CatalogDetailTransformer.CREATE_ENTITY_FOR_RELATION(dto.getBankType().getId()));
        entity.setName(dto.getName());
        entity.setActive(dto.getActive());
        return entity;
    }

    public static BankDto ENTITY_TO_DTO(BankBranchEntity entity, BankDto dto) {
        dto.setId(entity.getId());
        transformAuditingFields(entity, dto);
        dto.setCode(entity.getCode());
        Optional.ofNullable(entity.getAddress()).ifPresent(addressEntity -> dto.setAddress(AddressTransformer.ENTITY_TO_DTO(addressEntity, new AddressDto())));
        dto.setBankType(CatalogDetailTransformer.ENTITY_TO_DTO(entity.getBankType(), new CatalogDetailDto()));
        dto.setName(entity.getName());
        dto.setActive(entity.getActive());
        return dto;
    }


    public static BankBranchEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        BankBranchEntity entity = new BankBranchEntity();
        entity.setId(id);
        entity.setVersion(0l);
        return entity;
    }
}
