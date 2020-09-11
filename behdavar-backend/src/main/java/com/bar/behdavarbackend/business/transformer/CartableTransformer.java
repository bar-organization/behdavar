package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.CartableDto;
import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.CartableEntity;

public class CartableTransformer extends BaseAuditorTransformer {

    public static CartableEntity dtoToEntity(CartableDto dto, CartableEntity entity) {
        entity.setReceiver(UserTransformer.createEntityForRelation(dto.getReceiver().getId()));
        entity.setSender(UserTransformer.createEntityForRelation(dto.getSender().getId()));
        entity.setContract(ContractTransformer.createEntityForRelation(dto.getContract().getId()));
        entity.setActive(dto.getActive());
        return entity;
    }

    public static CartableDto entityToDto(CartableEntity entity, CartableDto dto) {
        transformAuditingFields(entity, dto);
        dto.setActive(entity.getActive());
        dto.setContract(ContractTransformer.entityToDto(entity.getContract(), new ContractDto()));
        dto.setReceiver(UserTransformer.entityToDto(entity.getReceiver(), new UserDto()));
        dto.setSender(UserTransformer.entityToDto(entity.getSender(), new UserDto()));
        return dto;
    }

    public static CartableEntity createEntityForRelation(Long id) {
        CartableEntity entity = new CartableEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
