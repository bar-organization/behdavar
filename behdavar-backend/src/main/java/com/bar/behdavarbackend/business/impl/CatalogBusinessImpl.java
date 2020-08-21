package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.CatalogBusiness;
import com.bar.behdavarbackend.business.transformer.CatalogTransformer;
import com.bar.behdavarbackend.dto.CatalogDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CatalogEntity;
import com.bar.behdavardatabase.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CatalogBusinessImpl.BEAN_NAME)
public class CatalogBusinessImpl implements CatalogBusiness {
    public static final String BEAN_NAME = "catalogBusinessImpl";

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public CatalogEntity findById(Long id) {
        return catalogRepository.findById(id).orElseThrow(() -> new BusinessException("error.catalog.not.found", id));
    }

    @Override
    public Long save(CatalogDto dto) {
        CatalogEntity catalogEntity = CatalogTransformer.DTO_TO_ENTITY(dto, new CatalogEntity());
        return catalogRepository.save(catalogEntity).getId();
    }

    @Override
    public void update(CatalogDto dto) {
        CatalogEntity catalogEntity = CatalogTransformer.DTO_TO_ENTITY(dto, findById(dto.getId()));
        catalogRepository.save(catalogEntity);
    }

    @Override
    public void delete(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(catalogRepository, pagingRequest);
        return executor.execute();
    }
}
