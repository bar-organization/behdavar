package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.AttachmentDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.AttachmentEntity;

import java.util.List;

public interface AttachmentBusiness {
    AttachmentEntity findById(Long id);

    Long save(AttachmentDto dto);

    void update(AttachmentDto dto);

    void delete(Long id);

    List<AttachmentDto> findByContract(Long contractId);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
