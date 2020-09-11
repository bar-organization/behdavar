package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PrivilegeDto;
import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavardatabase.entity.security.PrivilegeEntity;
import com.bar.behdavardatabase.entity.security.RoleEntity;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class RoleTransformer extends BaseAuditorTransformer {


    public static RoleEntity DTO_TO_ENTITY(RoleDto dto, RoleEntity entity) {
        entity.setName(dto.getRoleName());
        entity.setTitle(dto.getTitle());

        if (!CollectionUtils.isEmpty(dto.getPrivilegeDtos())) {
            Set<PrivilegeEntity> privilegeEntities = new HashSet<>();
            dto.getPrivilegeDtos().forEach(privilegeDto -> {
                privilegeEntities.add(PrivilegeTransformer.CREATE_ENTITY_FOR_RELATION(privilegeDto.getId()));
            });
            entity.setPrivileges(privilegeEntities);
        }
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
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        Set<PrivilegeEntity> privileges = entity.getPrivileges();
        transformAuditingFields(entity, dto);
        dto.setRoleName(entity.getName());
        dto.setPrivileges(getPrivileges(privileges));

        if (!CollectionUtils.isEmpty(entity.getPrivileges())) {
            List<PrivilegeDto> privilegeDtos = new ArrayList<>();
            if (fields.contains(RoleEntity.PRIVILEGES)) {
                entity.getPrivileges().forEach(e -> {
                    privilegeDtos.add(PrivilegeTransformer.ENTITY_TO_DTO(e, new PrivilegeDto()));
                });
            } else {
//                entity.getPrivileges().forEach(e ->
//                        privilegeDtos.add(PrivilegeTransformer.CREATE_DTO_FOR_RELATION(e.getId())));
            }
            dto.setPrivilegeDtos(privilegeDtos);
        }
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
