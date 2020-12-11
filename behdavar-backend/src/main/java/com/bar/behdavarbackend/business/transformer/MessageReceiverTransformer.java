package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.MessageDto;
import com.bar.behdavarbackend.dto.MessageReceiverDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.MessageReceiverEntity;

import java.util.Optional;

public class MessageReceiverTransformer extends BaseAuditorTransformer {

    public static MessageReceiverEntity dtoToEntity(MessageReceiverDto dto, MessageReceiverEntity entity) {
        entity.setDeleted(dto.getDeleted());
        entity.setIsCC(dto.getIsCC());
        Optional.ofNullable(dto.getMessage()).ifPresent(messageDto ->
        {
            entity.setMessage(MessageTransformer.createEntityForRelation(dto.getMessage().getId()));
        });

        Optional.ofNullable(dto.getReceiver()).ifPresent(userDto -> {
            entity.setReceiver(UserTransformer.createEntityForRelation(dto.getReceiver().getId()));
        });
        return entity;
    }

    public static MessageReceiverDto entityToDto(MessageReceiverEntity entity, MessageReceiverDto dto) {
        transformAuditingFields(entity, dto);
        dto.setDeleted(entity.getDeleted());
        dto.setIsCC(entity.getIsCC());
        dto.setMessage(MessageTransformer.entityToDto(entity.getMessage(), new MessageDto()));
        dto.setReceiver(UserTransformer.entityToDto(entity.getReceiver(), new UserDto()));
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
