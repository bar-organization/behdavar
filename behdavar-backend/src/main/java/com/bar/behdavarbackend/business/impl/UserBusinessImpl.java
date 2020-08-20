package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.UserBusiness;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessImpl implements UserBusiness {

    private final UserRepository userRepository;

    @Autowired
    public UserBusinessImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findByUserName(String username) throws BusinessException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException("Username not found"));
        return UserTransformer.ENTITY_TO_DTO(userEntity, new UserDto());
    }
}
