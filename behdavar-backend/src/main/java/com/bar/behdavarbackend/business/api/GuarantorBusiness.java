package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.GuarantorDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

import java.util.List;

public interface GuarantorBusiness {
    GuarantorDto findById(Long id);

    Long save(GuarantorDto dto);

    void update(GuarantorDto dto);

    void delete(Long id);

    List<GuarantorDto> findByContract(Long contractId);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
