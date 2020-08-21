package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.GeoDivisionBusiness;
import com.bar.behdavarbackend.business.transformer.GeoDivisionTransformer;
import com.bar.behdavarbackend.dto.GeoDivisionDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.GeoDivisionEntity;
import com.bar.behdavardatabase.repository.GeoDivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(GeoDivisionBusinessImpl.BEAN_NAME)
public class GeoDivisionBusinessImpl implements GeoDivisionBusiness {
    public static final String BEAN_NAME = "GeoDivisionBusinessImpl";

    @Autowired
    private GeoDivisionRepository repository;

    @Override
    public GeoDivisionDto findById(Long id) {
        return repository.findById(id)
                .map(geoDivisionEntity -> GeoDivisionTransformer.ENTITY_TO_DTO(geoDivisionEntity, new GeoDivisionDto()))
                .orElseThrow(() -> new BusinessException("error.GeoDivision.not.found", id));
    }

    @Override
    public Long save(GeoDivisionDto dto) {
        GeoDivisionEntity GeoDivisionEntity = GeoDivisionTransformer.DTO_TO_ENTITY(dto, new GeoDivisionEntity());
        return repository.save(GeoDivisionEntity).getId();
    }

    @Override
    public void update(GeoDivisionDto dto) {
        GeoDivisionEntity GeoDivisionEntity = GeoDivisionTransformer.DTO_TO_ENTITY(dto, repository
                .findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.GeoDivision.not.found", dto.getId())));
        repository.save(GeoDivisionEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PagingResponse<GeoDivisionDto> findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(repository, pagingRequest);
        return executor.execute();
    }
}
