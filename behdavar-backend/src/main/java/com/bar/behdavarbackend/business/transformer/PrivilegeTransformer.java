package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PrivilegeDto;
import com.bar.behdavarcommon.util.MessageUtil;
import com.bar.behdavardatabase.entity.security.PrivilegeEntity;

public class PrivilegeTransformer extends BaseAuditorTransformer {

    public static PrivilegeEntity dtoToEntity(PrivilegeDto dto, PrivilegeEntity entity) {
        entity.setName(dto.getName());
        return entity;
    }

    public static PrivilegeDto entityToDto(PrivilegeEntity entity, PrivilegeDto dto, String... strings) {
        transformAuditingFields(entity, dto);
        dto.setName(entity.getName());
        dto.setTitle(MessageUtil.getMessage(entity.getName()));
        return dto;
    }

    public static PrivilegeEntity createEntityForRelation(Long id) {
        PrivilegeEntity entity = new PrivilegeEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static PrivilegeDto createDtoForRelation(Long id) {
        PrivilegeDto dto = new PrivilegeDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
