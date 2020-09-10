package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.PrivilegeDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

public interface PrivilegeBusiness {
    PrivilegeDto findById(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
