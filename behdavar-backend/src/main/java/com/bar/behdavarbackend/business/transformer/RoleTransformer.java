package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavardatabase.entity.security.PrivilegeEntity;
import com.bar.behdavardatabase.entity.security.RoleEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleTransformer {
    public static RoleDto ENTITY_TO_DTO(RoleEntity roleEntity, RoleDto roleDto) {
        Set<PrivilegeEntity> privileges = roleEntity.getPrivileges();

        roleDto.setRoleName(roleEntity.getName());
        roleDto.setPrivileges(getPrivileges(privileges));

        return roleDto;
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
