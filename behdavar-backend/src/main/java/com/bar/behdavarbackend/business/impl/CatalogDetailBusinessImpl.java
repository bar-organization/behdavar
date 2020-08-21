package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.CatalogDetailBusiness;
import com.bar.behdavarbackend.business.transformer.CatalogDetailTransformer;
import com.bar.behdavarbackend.dto.CatalogDetailDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CatalogDetailEntity;
import com.bar.behdavardatabase.repository.CatalogDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CatalogDetailBusinessImpl.BEAN_NAME)
public class CatalogDetailBusinessImpl implements CatalogDetailBusiness {
    public static final String BEAN_NAME = "CatalogDetailBusinessImpl";

    @Autowired
    private CatalogDetailRepository CatalogDetailRepository;

    @Override
    public CatalogDetailEntity findById(Long id) {
        return CatalogDetailRepository.findById(id).orElseThrow(() -> new BusinessException("error.CatalogDetail.not.found", id));
    }

    @Override
    public Long save(CatalogDetailDto dto) {
        CatalogDetailEntity CatalogDetailEntity = CatalogDetailTransformer.DTO_TO_ENTITY(dto, new CatalogDetailEntity());
        return CatalogDetailRepository.save(CatalogDetailEntity).getId();
    }

    @Override
    public void update(CatalogDetailDto dto) {
        CatalogDetailEntity CatalogDetailEntity = CatalogDetailTransformer.DTO_TO_ENTITY(dto, findById(dto.getId()));
        CatalogDetailRepository.save(CatalogDetailEntity);
    }

    @Override
    public void delete(Long id) {
        CatalogDetailRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(CatalogDetailRepository, pagingRequest);
        return executor.execute();
    }
}
