package com.bar.behdavarbackend.util.pagination;

import com.bar.behdavardatabase.common.BaseEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

@AllArgsConstructor
public class PagingExecutor<E extends BaseEntity<I>, I extends Serializable> {
    private final AbstractRepository<E, I> repository;
    private final PagingRequest pagingRequest;

    public PagingResponse<E> execute() {
        Page<E> page = null;
        if (!pagingRequest.getFilters().isEmpty()) {
            CommonSpecification<E> specification = new CommonSpecification<>(pagingRequest.getFilters());
            Pageable pageable = PageRequest.of(pagingRequest.getStart(), pagingRequest.getMax());
            page = repository.findAll(specification, pageable);
        } else {
            Pageable pageable = PageRequest.of(pagingRequest.getStart(), pagingRequest.getMax());
            page = repository.findAll(pageable);
        }
        return new PagingResponse<E>(pagingRequest.getStart(), pagingRequest.getMax(), page.getTotalElements(), page.getContent());

    }
}
