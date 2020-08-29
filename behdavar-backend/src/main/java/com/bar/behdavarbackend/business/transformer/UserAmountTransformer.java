package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.UserAmountDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.UserAmountEntity;

public class UserAmountTransformer {

    public static UserAmountEntity DTO_TO_ENTITY(UserAmountDto dto, UserAmountEntity entity) {
        entity.setReceiveAmount(dto.getReceiveAmount());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setUser(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getUser().getId()));
        return entity;
    }

    public static UserAmountDto ENTITY_TO_DTO(UserAmountEntity entity, UserAmountDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setReceiveAmount(entity.getReceiveAmount());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setUser(UserTransformer.ENTITY_TO_DTO(entity.getUser(), new UserDto()));
        return dto;
    }

    public static UserAmountEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        UserAmountEntity entity = new UserAmountEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
