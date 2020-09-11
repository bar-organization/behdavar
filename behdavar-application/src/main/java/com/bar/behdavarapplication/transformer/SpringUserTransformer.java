package com.bar.behdavarapplication.transformer;

import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavarbackend.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

public class SpringUserTransformer {
    public static User DTO_TO_SPRING_USER(UserDto userDto) {

        return new User(userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEnabled(),
                userDto.getIsAccountNonExpired(),
                userDto.getIsCredentialsNonExpired(),
                userDto.getIsAccountNonLocked(),
                getAuthorities(userDto.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<RoleDto> roles) {
        if (Objects.isNull(roles) || roles.isEmpty())
            return Collections.emptyList();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RoleDto role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);

            if (Objects.nonNull(role.getPrivileges()) && !role.getPrivileges().isEmpty())
                role.getPrivileges().forEach(privilege -> authorities.add(new SimpleGrantedAuthority(privilege)));
        }

        return authorities;
    }

}
