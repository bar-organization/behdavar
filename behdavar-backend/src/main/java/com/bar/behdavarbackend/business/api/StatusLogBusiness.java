package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.StatusLogDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

public interface StatusLogBusiness {
    StatusLogDto findById(Long id);

    Long save(StatusLogDto dto);

    void update(StatusLogDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
