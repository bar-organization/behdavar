package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavardatabase.entity.security.PrivilegeEntity;
import com.bar.behdavardatabase.entity.security.RoleEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleTransformer extends BaseAuditorTransformer {


    public static RoleEntity DTO_TO_ENTITY(RoleDto dto, RoleEntity entity) {
        entity.setName(dto.getRoleName());
        entity.setTitle(dto.getTitle());

        return entity;
    }

    private static List<String> getPrivileges(Set<PrivilegeEntity> privileges) {
        if (Objects.isNull(privileges) || privileges.isEmpty())
            return Collections.emptyList();

        return privileges
                .stream()
                .map(PrivilegeEntity::getName)
                .collect(Collectors.toList());
    }

    public static RoleDto ENTITY_TO_DTO(RoleEntity entity, RoleDto dto, String... strings) {
        Set<PrivilegeEntity> privileges = entity.getPrivileges();
        transformAuditingFields(entity, dto);
        dto.setRoleName(entity.getName());
        dto.setPrivileges(getPrivileges(privileges));

        return dto;
    }

    public static RoleEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        RoleEntity entity = new RoleEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static RoleDto CREATE_DTO_FOR_RELATION(Long id) {
        RoleDto dto = new RoleDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
