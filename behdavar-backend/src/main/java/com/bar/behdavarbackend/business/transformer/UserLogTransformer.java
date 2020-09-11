package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.dto.UserLogDto;
import com.bar.behdavardatabase.entity.UserLogEntity;

public class UserLogTransformer extends BaseAuditorTransformer {

    public static UserLogEntity dtoToEntity(UserLogDto dto, UserLogEntity entity) {
        entity.setLastLogin(dto.getLastLogin());
        entity.setUser(UserTransformer.createEntityForRelation(dto.getUser().getId()));
        return entity;
    }


    public static UserLogDto entityToDto(UserLogEntity entity, UserLogDto dto) {
        dto.setLastLogin(entity.getLastLogin());
        dto.setUser(UserTransformer.entityToDto(entity.getUser(), new UserDto()));
        return dto;
    }


    public static UserLogEntity createEntityForRelation(Long id) {
        UserLogEntity entity = new UserLogEntity();
        entity.setId(id);
        return entity;
    }

    public static UserLogDto createDtoForRelation(Long id) {
        UserLogDto dto = new UserLogDto();
        dto.setId(id);
        return dto;
    }

}
