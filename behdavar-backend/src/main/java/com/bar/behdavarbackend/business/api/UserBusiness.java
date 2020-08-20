package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.exception.BusinessException;

public interface UserBusiness {
    UserDto findByUserName(String username) throws BusinessException;
}
