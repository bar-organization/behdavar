package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.UserAmountDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.UserAmountEntity;

public class UserAmountTransformer extends BaseAuditorTransformer {

    public static UserAmountEntity dtoToEntity(UserAmountDto dto, UserAmountEntity entity) {
        entity.setReceiveAmount(dto.getReceiveAmount());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setUser(UserTransformer.createEntityForRelation(dto.getUser().getId()));
        return entity;
    }

    public static UserAmountDto entityToDto(UserAmountEntity entity, UserAmountDto dto) {
        transformAuditingFields(entity, dto);
        dto.setReceiveAmount(entity.getReceiveAmount());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setUser(UserTransformer.entityToDto(entity.getUser(), new UserDto()));
        return dto;
    }

    public static UserAmountEntity createEntityForRelation(Long id) {
        UserAmountEntity entity = new UserAmountEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
