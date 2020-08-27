package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

public interface PursuitBusiness {
    PursuitDto findById(Long id);

    Long save(PursuitDto dto);

    void update(PursuitDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);

    PursuitDto findByContractId(Long id);
}
