package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.dto.UserLogDto;
import com.bar.behdavardatabase.entity.UserLogEntity;

public class UserLogTransformer extends BaseAuditorTransformer {

    public static UserLogEntity DTO_TO_ENTITY(UserLogDto dto, UserLogEntity entity) {
        entity.setLastLogin(dto.getLastLogin());
        entity.setUser(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getUser().getId()));
        return entity;
    }


    public static UserLogDto ENTITY_TO_DTO(UserLogEntity entity, UserLogDto dto) {
        dto.setLastLogin(entity.getLastLogin());
        dto.setUser(UserTransformer.ENTITY_TO_DTO(entity.getUser(), new UserDto()));
        return dto;
    }


    public static UserLogEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        UserLogEntity UserLogEntity = new UserLogEntity();
        UserLogEntity.setId(id);
        return UserLogEntity;
    }

    public static UserLogDto CREATE_DTO_FOR_RELATION(Long id) {
        UserLogDto dto = new UserLogDto();
        dto.setId(id);
        return dto;
    }

}
