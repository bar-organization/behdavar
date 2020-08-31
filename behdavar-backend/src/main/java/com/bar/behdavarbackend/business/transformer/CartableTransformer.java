package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.CartableDto;
import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.CartableEntity;

public class CartableTransformer extends BaseAuditorTransformer {

    public static CartableEntity DTO_TO_ENTITY(CartableDto dto, CartableEntity entity) {
        entity.setReceiver(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getReceiver().getId()));
        entity.setSender(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getSender().getId()));
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setActive(dto.getActive());
        return entity;
    }

    public static CartableDto ENTITY_TO_DTO(CartableEntity entity, CartableDto dto) {
        transformAuditingFields(entity, dto);
        dto.setActive(entity.getActive());
        dto.setContract(ContractTransformer.ENTITY_TO_DTO(entity.getContract(), new ContractDto()));
        dto.setReceiver(UserTransformer.ENTITY_TO_DTO(entity.getReceiver(), new UserDto()));
        dto.setSender(UserTransformer.ENTITY_TO_DTO(entity.getSender(), new UserDto()));
        return dto;
    }

    public static CartableEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        CartableEntity entity = new CartableEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
