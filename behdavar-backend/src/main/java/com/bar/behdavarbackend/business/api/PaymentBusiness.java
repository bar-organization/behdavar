package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

import java.util.List;

public interface PaymentBusiness {
    PaymentDto findById(Long id);

    Long save(PaymentDto dto);

    void update(PaymentDto dto);

    void delete(Long id);

    PagingResponse findPaging(PagingRequest pagingRequest);

    List<PaymentDto> findByContractId(Long id);

    Long getTotalDepositAmount(Long contractId);
}
