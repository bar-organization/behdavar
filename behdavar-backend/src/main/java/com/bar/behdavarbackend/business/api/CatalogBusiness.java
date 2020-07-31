package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.CatalogDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CatalogEntity;

public interface CatalogBusiness {
    CatalogEntity findById(Long id);

    Long save(CatalogDto dto);

    void update(CatalogDto dto);

    void delete(Long id);

    PagingResponse<CatalogDto> findPaging(PagingRequest pagingRequest);
}
