package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.MessageDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.MessageEntity;

public class MessageTransformer extends  BaseAuditorTransformer {

    public static MessageEntity dtoToEntity(MessageDto dto, MessageEntity entity) {
        entity.setDescription(dto.getDescription());
        entity.setSender(UserTransformer.createEntityForRelation(dto.getSender().getId()));
        entity.setActive(dto.getActive());
        entity.setIsSent(dto.getIsSent());
        return entity;
    }

    public static MessageDto entityToDto(MessageEntity entity,MessageDto dto) {
        transformAuditingFields(entity, dto);
        dto.setActive(entity.getActive());
        dto.setDescription(entity.getDescription());
        dto.setIsSent(entity.getIsSent());
        dto.setMessageNumber(entity.getMessageNumber());
        dto.setSender(UserTransformer.entityToDto(entity.getSender(),new UserDto()));
        return dto;
    }

    public static MessageEntity createEntityForRelation(Long id) {
        MessageEntity entity = new MessageEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static MessageDto createDtoForRelation(Long id) {
        MessageDto dto = new MessageDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
