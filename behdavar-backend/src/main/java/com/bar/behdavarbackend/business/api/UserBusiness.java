package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

public interface UserBusiness {
    UserDto findByUserName(String username) throws BusinessException;

    UserDto findById(Long id);

    Long save(UserDto dto);

    void update(UserDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
