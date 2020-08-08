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
    private GeoDivisionRepository GeoDivisionRepository;

    @Override
    public GeoDivisionEntity findById(Long id) {
        return GeoDivisionRepository.findById(id).orElseThrow(() -> new BusinessException("error.GeoDivision.not.found", id));
    }

    @Override
    public Long save(GeoDivisionDto dto) {
        GeoDivisionEntity GeoDivisionEntity = GeoDivisionTransformer.DTO_TO_ENTITY(dto, new GeoDivisionEntity());
        return GeoDivisionRepository.save(GeoDivisionEntity).getId();
    }

    @Override
    public void update(GeoDivisionDto dto) {
        GeoDivisionEntity GeoDivisionEntity = GeoDivisionTransformer.DTO_TO_ENTITY(dto, findById(dto.getId()));
        GeoDivisionRepository.save(GeoDivisionEntity);
    }

    @Override
    public void delete(Long id) {
        GeoDivisionRepository.deleteById(id);
    }

    @Override
    public PagingResponse<GeoDivisionDto> findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(GeoDivisionRepository, pagingRequest);
        return executor.execute();
    }
}
