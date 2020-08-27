package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CustomerEntity;

import java.util.List;

public interface CustomerBusiness {
    CustomerEntity findById(Long id);

    Long save(CustomerDto dto);

    void update(CustomerDto dto);

    void delete(Long id);

    List<CustomerDto> findByContract(Long contractId);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
