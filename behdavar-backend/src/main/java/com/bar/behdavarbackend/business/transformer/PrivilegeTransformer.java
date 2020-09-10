package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PrivilegeDto;
import com.bar.behdavarcommon.util.MessageUtil;
import com.bar.behdavardatabase.entity.security.PrivilegeEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrivilegeTransformer extends BaseAuditorTransformer {

    public static PrivilegeEntity DTO_TO_ENTITY(PrivilegeDto dto, PrivilegeEntity entity) {
        entity.setName(dto.getName());
        return entity;
    }

    public static PrivilegeDto ENTITY_TO_DTO(PrivilegeEntity entity, PrivilegeDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setName(entity.getName());
        dto.setTitle(MessageUtil.getMessage(entity.getName()));
        return dto;
    }

    public static PrivilegeEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        PrivilegeEntity entity = new PrivilegeEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static PrivilegeDto CREATE_DTO_FOR_RELATION(Long id) {
        PrivilegeDto dto = new PrivilegeDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
