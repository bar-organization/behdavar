package com.bar.behdavarapplication.model;

import com.bar.behdavarbackend.dto.UserDto;

public class AuthenticationResponse {
    private final String jwt;
    private final UserDto userDto;
    public AuthenticationResponse(String jwt, UserDto userDto) {
        this.jwt = jwt;
        this.userDto = userDto;
    }

    public String getJwt() {
        return jwt;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
