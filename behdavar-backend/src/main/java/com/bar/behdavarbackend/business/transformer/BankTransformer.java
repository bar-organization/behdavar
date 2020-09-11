package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.AddressDto;
import com.bar.behdavarbackend.dto.BankDto;
import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavardatabase.entity.BankBranchEntity;

import java.util.Optional;

public class BankTransformer extends BaseAuditorTransformer {

    public static BankBranchEntity dtoToEntity(BankDto dto, BankBranchEntity entity) {
        entity.setCode(dto.getCode());
        Optional.ofNullable(dto.getAddress()).ifPresent(addressDto -> entity.setAddress(AddressTransformer.createEntityForRelation(addressDto.getId())));
        entity.setBankType(CatalogDetailTransformer.createEntityForRelation(dto.getBankType().getId()));
        entity.setName(dto.getName());
        entity.setActive(dto.getActive());
        return entity;
    }

    public static BankDto entityToDto(BankBranchEntity entity, BankDto dto) {
        transformAuditingFields(entity, dto);
        dto.setCode(entity.getCode());
        Optional.ofNullable(entity.getAddress()).ifPresent(addressEntity -> dto.setAddress(AddressTransformer.entityToDto(addressEntity, new AddressDto())));
        dto.setBankType(CatalogDetailTransformer.entityToDto(entity.getBankType(), new CatalogDetailDto()));
        dto.setName(entity.getName());
        dto.setActive(entity.getActive());
        return dto;
    }


    public static BankBranchEntity createEntityForRelation(Long id) {
        BankBranchEntity entity = new BankBranchEntity();
        entity.setId(id);
        entity.setVersion(0l);
        return entity;
    }
}
