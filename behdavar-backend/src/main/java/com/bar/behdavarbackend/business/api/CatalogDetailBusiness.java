package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CatalogDetailEntity;

public interface CatalogDetailBusiness {
    CatalogDetailEntity findById(Long id);

    Long save(CatalogDetailDto dto);

    void update(CatalogDetailDto dto);

    void delete(Long id);

    PagingResponse<CatalogDetailDto> findPaging(PagingRequest pagingRequest);
}
