package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.PursuitLogDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.PursuitEntity;

public interface PursuitLogBusiness {
    PursuitLogDto findById(Long id);

    Long save(PursuitEntity pursuitEntity);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
