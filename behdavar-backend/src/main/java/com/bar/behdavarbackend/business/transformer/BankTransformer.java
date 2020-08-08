package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.BankDto;
import com.bar.behdavardatabase.entity.BankEntity;

public class BankTransformer {

    public static BankEntity DTO_TO_ENTITY(BankDto dto, BankEntity entity) {
        entity.setCode(dto.getCode());
        entity.setAddress(dto.getAddress());
        entity.setBankType(entity.getBankType());
        entity.setCityType(dto.getCityType());
        entity.setCity(GeoDivisionTransformer.CREATE_ENTITY_FOR_RELATION(dto.getCity().getId()));
        entity.setFax(dto.getFax());
        entity.setHeads(dto.getHeads());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        return entity;
    }

    public static BankEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        BankEntity entity = new BankEntity();
        entity.setId(id);
        return entity;
    }
}
