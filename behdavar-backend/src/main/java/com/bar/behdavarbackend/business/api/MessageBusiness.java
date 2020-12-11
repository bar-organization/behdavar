package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.MessageDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;

import java.util.List;

public interface MessageBusiness {
    MessageDto findById(Long id);

    Long saveDraft(MessageDto dto);

    Long send(MessageDto dto);

    List<MessageDto> getInbox();

    void update(MessageDto dto);

    void delete(Long id);

    PagingResponse getInbox(PagingRequest pagingRequest);

    PagingResponse getSentItems(PagingRequest pagingRequest);

    PagingResponse getDrafts(PagingRequest pagingRequest);

    PagingResponse findPaging(PagingRequest pagingRequest);
}
