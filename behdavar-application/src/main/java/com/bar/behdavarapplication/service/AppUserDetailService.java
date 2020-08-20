package com.bar.behdavarapplication.service;

import com.bar.behdavarbackend.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserDetailService extends UserDetailsService {

    UserDetails loadUserByUserDto(UserDto userDto);
}
