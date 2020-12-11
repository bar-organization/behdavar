package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.MessageAttachmentDto;
import com.bar.behdavarbackend.dto.MessageDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.MessageAttachmentEntity;
import com.bar.behdavardatabase.entity.MessageEntity;
import com.bar.behdavardatabase.entity.MessageReceiverEntity;

import java.util.*;
import java.util.stream.Collectors;

public class MessageTransformer extends BaseAuditorTransformer {

    public static MessageEntity dtoToEntity(MessageDto dto, MessageEntity entity) {
        entity.setDescription(dto.getDescription());
        entity.setSubject(dto.getSubject());


        // Receivers
        Optional.ofNullable(dto.getReceivers()).ifPresent(messageReceiverDtos -> {
            messageReceiverDtos.forEach(messageReceiverDto -> {
                entity.getReceivers().add(MessageReceiverTransformer.dtoToEntity(messageReceiverDto, new MessageReceiverEntity()));
            });
            entity.getReceivers().forEach(e -> e.setIsCC(Boolean.FALSE));
        });


        // CC
        Optional.ofNullable(dto.getCcs()).ifPresent(cc -> {
            Set<MessageReceiverEntity> ccs = new HashSet<>();
            cc.forEach(messageReceiverDto ->
                    ccs.add(MessageReceiverTransformer.dtoToEntity(messageReceiverDto, new MessageReceiverEntity()))
            );
            ccs.forEach(e -> e.setIsCC(Boolean.TRUE));
            entity.getReceivers().addAll(ccs);
        });
        entity.getReceivers().forEach(r -> r.setMessage(entity));
        //Attachments
        Optional.ofNullable(dto.getAttachments()).ifPresent(messageAttachmentDtos -> {
            messageAttachmentDtos.forEach(messageAttachmentDto ->
                    entity.getAttachments().add(MessageAttachmentTransformer.dtoToEntity(messageAttachmentDto, new MessageAttachmentEntity()))
            );
            entity.getAttachments().forEach(ma -> ma.setMessage(entity));
        });


        return entity;
    }

    public static MessageDto entityToDto(MessageEntity entity, MessageDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setDeleted(entity.getDeleted());
        dto.setDescription(entity.getDescription());
        dto.setIsSent(entity.getIsSent());
        dto.setMessageNumber(entity.getMessageNumber());
        dto.setSender(UserTransformer.entityToDto(entity.getSender(), new UserDto()));
        if (fields.contains(MessageEntity.ATTACHMENTS)) {
            Optional.ofNullable(entity.getAttachments()).ifPresent(messageAttachmentEntities -> {
                messageAttachmentEntities.forEach(mae ->
                        dto.getAttachments().add(MessageAttachmentTransformer.entityToDto(mae, new MessageAttachmentDto())));
            });
        }
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
