package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.dto.UserLogDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.UserLogEntity;

import java.time.LocalDateTime;

public interface UserLogBusiness {
    UserLogEntity findById(Long id);

    Long save(UserLogDto dto);

    void update(UserLogDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);

    void writeLoginLog(UserDto dto);

    LocalDateTime getLastLogin();
}
