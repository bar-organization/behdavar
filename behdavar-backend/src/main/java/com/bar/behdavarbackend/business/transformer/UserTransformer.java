package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PersonEntity;
import com.bar.behdavardatabase.entity.security.RoleEntity;
import com.bar.behdavardatabase.entity.security.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

public class UserTransformer extends BaseAuditorTransformer {
    public static UserDto ENTITY_TO_DTO(UserEntity entity, UserDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        @NotNull PersonEntity person = entity.getPerson();
        Set<RoleEntity> roles = entity.getRoles();
        transformAuditingFields(entity, dto);
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEnabled(entity.isEnabled());
        dto.setTokenExpired(entity.isTokenExpired());
        dto.setAccountNonExpired(entity.isAccountNonExpired());
        dto.setAccountNonLocked(entity.isAccountNonLocked());
        dto.setCredentialsNonExpired(entity.isCredentialsNonExpired());
        if (fields.contains(UserEntity.ROLES)) {
            dto.setRoles(getUserRoles(roles));
        } else {
            if (!CollectionUtils.isEmpty(entity.getRoles())) {
                List<RoleDto> roleDtos = new ArrayList<>();
                entity.getRoles().forEach(r -> roleDtos.add(RoleTransformer.ENTITY_TO_DTO(r, new RoleDto())));
                dto.setRoles(roleDtos);
            }
        }

        return dto;
    }

    public static UserEntity DTO_TO_ENTITY(UserDto dto, UserEntity entity) {
        entity.setEnabled(dto.getEnabled());
        entity.setTokenExpired(dto.getTokenExpired());
        entity.setAccountNonExpired(dto.getIsAccountNonExpired());
        entity.setAccountNonLocked(dto.getIsAccountNonLocked());
        entity.setCredentialsNonExpired(dto.getIsCredentialsNonExpired());
        if (dto.getId() == null) {
            entity.setUsername(dto.getUsername());
            entity.setPerson(PersonTransformer.CREATE_ENTITY_FOR_RELATION(dto.getPerson().getId()));
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        }

        if (!CollectionUtils.isEmpty(dto.getRoles())) {
            Set<RoleEntity> roleEntities = new HashSet<>();
            dto.getRoles().forEach(r -> roleEntities.add(RoleTransformer.CREATE_ENTITY_FOR_RELATION(r.getId())));
            entity.setRoles(roleEntities);
        }
        entity.setDeleted(false);
        return entity;
    }

    private static List<RoleDto> getUserRoles(Set<RoleEntity> roles) {
        if (Objects.isNull(roles) || roles.isEmpty())
            return Collections.emptyList();

        return roles
                .stream()
                .map(roleEntity -> RoleTransformer.ENTITY_TO_DTO(roleEntity, new RoleDto()))
                .collect(Collectors.toList());
    }

    public static UserEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setVersion(0L);
        return userEntity;
    }

    public static UserDto CREATE_DTO_FOR_RELATION(Long id) {
        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }

}
