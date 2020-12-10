package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.MessageDto;
import com.bar.behdavarbackend.dto.MessageReceiverDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.MessageReceiverEntity;

public class MessageReceiverTransformer extends  BaseAuditorTransformer {

    public static MessageReceiverEntity dtoToEntity(MessageReceiverDto dto,MessageReceiverEntity entity) {
        entity.setActive(dto.getActive());
        entity.setIsCC(dto.getIsCC());
        entity.setMessage(MessageTransformer.createEntityForRelation(dto.getMessage().getId()));
        entity.setReceiver(UserTransformer.createEntityForRelation(dto.getReceiver().getId()));
        return entity;
    }

    public static MessageReceiverDto entityToDto(MessageReceiverEntity entity,MessageReceiverDto dto) {
        transformAuditingFields(entity, dto);
        dto.setActive(entity.getActive());
        dto.setIsCC(entity.getIsCC());
        dto.setMessage(MessageTransformer.entityToDto(entity.getMessage(),new MessageDto()));
        dto.setReceiver(UserTransformer.entityToDto(entity.getReceiver(),new UserDto()));
        return dto;
    }

    public static MessageReceiverEntity createEntityForRelation(Long id) {
        MessageReceiverEntity entity = new MessageReceiverEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static MessageReceiverDto createDtoForRelation(Long id) {
        MessageReceiverDto dto = new MessageReceiverDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
