package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Setter
public class UserDto extends BaseAuditorDto<String, Long> {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private List<RoleDto> roles;
    private PersonDto person;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public boolean getTokenExpired() {
        return tokenExpired;
    }

    public boolean getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public PersonDto getPerson() {
        return person;
    }
}
