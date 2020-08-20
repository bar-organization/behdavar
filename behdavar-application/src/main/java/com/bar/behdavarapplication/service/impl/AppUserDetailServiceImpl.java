package com.bar.behdavarapplication.service.impl;

import com.bar.behdavarapplication.service.AppUserDetailService;
import com.bar.behdavarapplication.transformer.SpringUserTransformer;
import com.bar.behdavarbackend.business.api.UserBusiness;
import com.bar.behdavarbackend.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailServiceImpl implements AppUserDetailService {
    private final UserBusiness userBusiness;

    public AppUserDetailServiceImpl(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userBusiness.findByUserName(username);
        return SpringUserTransformer.DTO_TO_SPRING_USER(userDto);
    }


    @Override
    public UserDetails loadUserByUserDto(UserDto userDto) {
        return SpringUserTransformer.DTO_TO_SPRING_USER(userDto);
    }
}
