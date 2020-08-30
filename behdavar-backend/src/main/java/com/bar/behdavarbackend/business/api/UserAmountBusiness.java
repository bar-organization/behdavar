package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.UserAmountDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

import java.math.BigDecimal;

public interface UserAmountBusiness {
    UserAmountDto findById(Long id);

    Long save(UserAmountDto dto);

    void update(UserAmountDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);

    void increaseReceiveAmount(BigDecimal amount);

    UserAmountDto findByUserId(Long userId);
}
