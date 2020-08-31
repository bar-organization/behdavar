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
    public static RoleDto ENTITY_TO_DTO(RoleEntity entity, RoleDto dto) {
        Set<PrivilegeEntity> privileges = entity.getPrivileges();
        transformAuditingFields(entity, dto);
        dto.setRoleName(entity.getName());
        dto.setPrivileges(getPrivileges(privileges));

        return dto;
    }

    private static List<String> getPrivileges(Set<PrivilegeEntity> privileges) {
        if (Objects.isNull(privileges) || privileges.isEmpty())
            return Collections.emptyList();

        return privileges
                .stream()
                .map(PrivilegeEntity::getName)
                .collect(Collectors.toList());
    }
}
