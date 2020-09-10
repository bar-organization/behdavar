package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

public interface RoleBusiness {
    RoleDto findById(Long id);

    Long save(RoleDto dto);

    void update(RoleDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
