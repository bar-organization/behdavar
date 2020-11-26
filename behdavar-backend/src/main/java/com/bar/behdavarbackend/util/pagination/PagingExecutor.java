package com.bar.behdavarbackend.util.pagination;

import com.bar.behdavardatabase.common.BaseEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@AllArgsConstructor
public class PagingExecutor<E extends BaseEntity<I>, I extends Serializable> {
    private final AbstractRepository<E, I> repository;
    private final PagingRequest pagingRequest;

    public PagingResponse execute() {
        Page<E> page = null;
        if (!pagingRequest.getFilters().isEmpty()) {
            CommonSpecification<E> specification = new CommonSpecification<>(pagingRequest.getFilters());
            page = repository.findAll(specification, getPageableWithSort());
        } else {
            page = repository.findAll(getPageableWithSort());
        }
        return new PagingResponse(pagingRequest.getStart(), pagingRequest.getMax(), page.getTotalElements(), page.getContent());

    }

    private Pageable getPageableWithSort() {
        SortOperation sort = pagingRequest.getSort();
        if (sort == null || sort.getSortBy() == null) {
            return PageRequest.of(pagingRequest.getStart(), pagingRequest.getMax());
        }

        Sort requestedSort = sort.getDirection() == null ? Sort.by(sort.getSortBy()) : Sort.by(sort.getDirection(), sort.getSortBy());

        return PageRequest.of(pagingRequest.getStart(), pagingRequest.getMax(), requestedSort);
    }
}
