package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.MessageAttachmentDto;
import com.bar.behdavarbackend.dto.MessageDto;
import com.bar.behdavardatabase.entity.MessageAttachmentEntity;
import org.springframework.util.Base64Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageAttachmentTransformer extends BaseAuditorTransformer {

    public static MessageAttachmentEntity dtoToEntity(MessageAttachmentDto dto, MessageAttachmentEntity entity) {
        entity.setFileName(dto.getFileName());
        entity.setContent(Base64Utils.decodeFromString(dto.getContent()));
        Optional.ofNullable(dto.getMessage()).ifPresent(contractDto ->
                entity.setMessage(MessageTransformer.createEntityForRelation(contractDto.getId())));


        return entity;
    }

    public static MessageAttachmentDto entityToDto(MessageAttachmentEntity entity, MessageAttachmentDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setFileName(entity.getFileName());
        dto.setContent(Base64Utils.encodeToString(entity.getContent()));
        if (fields.contains(MessageAttachmentEntity.MESSAGE)) {
            dto.setMessage(MessageTransformer.entityToDto(entity.getMessage(), new MessageDto()));
        } else {
            dto.setMessage(MessageTransformer.createDtoForRelation(entity.getMessage().getId()));
        }

        return dto;
    }

    public static MessageAttachmentEntity createEntityForRelation(Long id) {
        MessageAttachmentEntity entity = new MessageAttachmentEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static MessageAttachmentDto createDtoForRelation(Long id) {
        MessageAttachmentDto dto = new MessageAttachmentDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
