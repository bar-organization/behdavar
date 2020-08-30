package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

public interface CatalogDetailBusiness {
    CatalogDetailDto findById(Long id);

    Long save(CatalogDetailDto dto);

    void update(CatalogDetailDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
