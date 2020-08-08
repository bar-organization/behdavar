package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.GeoDivisionDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.GeoDivisionEntity;

public interface GeoDivisionBusiness {
    GeoDivisionEntity findById(Long id);

    Long save(GeoDivisionDto dto);

    void update(GeoDivisionDto dto);

    void delete(Long id);

    PagingResponse<GeoDivisionDto> findPaging(PagingRequest pagingRequest);
}
