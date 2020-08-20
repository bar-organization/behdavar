package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PersonEntity;
import com.bar.behdavardatabase.entity.security.RoleEntity;
import com.bar.behdavardatabase.entity.security.UserEntity;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserTransformer {
    public static UserDto ENTITY_TO_DTO(UserEntity entity, UserDto dto) {
        @NotNull PersonEntity person = entity.getPerson();
        Set<RoleEntity> roles = entity.getRoles();

        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEnabled(entity.isEnabled());
        dto.setTokenExpired(entity.isTokenExpired());
        dto.setAccountNonExpired(entity.isAccountNonExpired());
        dto.setAccountNonLocked(entity.isAccountNonLocked());
        dto.setCredentialsNonExpired(entity.isCredentialsNonExpired());
        dto.setRoles(getUserRoles(roles));

        return dto;
    }
    private static List<RoleDto> getUserRoles(Set<RoleEntity> roles) {
        if(Objects.isNull(roles) || roles.isEmpty())
            return Collections.emptyList();

        return roles
                .stream()
                .map(roleEntity -> RoleTransformer.ENTITY_TO_DTO(roleEntity,new RoleDto()))
                .collect(Collectors.toList());
    }

}
