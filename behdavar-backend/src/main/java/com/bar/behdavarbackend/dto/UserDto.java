package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class UserDto extends BaseAuditorDto<String, Long> {
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private boolean enabled;
    private boolean tokenExpired;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private List<RoleDto> roles;
    private PersonDto person;

}
