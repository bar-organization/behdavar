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

import java.util.ArrayList;
import java.util.List;

@Service(CatalogDetailBusinessImpl.BEAN_NAME)
public class CatalogDetailBusinessImpl implements CatalogDetailBusiness {
    public static final String BEAN_NAME = "CatalogDetailBusinessImpl";

    @Autowired
    private CatalogDetailRepository catalogDetailRepository;

    @Override
    public CatalogDetailDto findById(Long id) {
        CatalogDetailEntity catalogDetailEntity = catalogDetailRepository.findById(id).orElseThrow(() -> new BusinessException("error.CatalogDetail.not.found", id));
        return CatalogDetailTransformer.entityToDto(catalogDetailEntity, new CatalogDetailDto());
    }

    @Override
    public Long save(CatalogDetailDto dto) {
        CatalogDetailEntity catalogDetailEntity = CatalogDetailTransformer.dtoToEntity(dto, new CatalogDetailEntity());
        return catalogDetailRepository.save(catalogDetailEntity).getId();
    }

    @Override
    public void update(CatalogDetailDto dto) {
        CatalogDetailEntity catalogDetailEntity = CatalogDetailTransformer.dtoToEntity(dto, catalogDetailRepository.findById(dto.getId()).orElseThrow(() -> new BusinessException("error.CatalogDetail.not.found", dto.getId())));
        catalogDetailRepository.save(catalogDetailEntity);
    }

    @Override
    public void delete(Long id) {
        catalogDetailRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<CatalogDetailEntity, Long> executor = new PagingExecutor<>(catalogDetailRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<CatalogDetailEntity> data = (List<CatalogDetailEntity>) pagingResponse.getData();
            List<CatalogDetailDto> output = new ArrayList<>();
            data.forEach(e -> output.add(CatalogDetailTransformer.entityToDto(e, new CatalogDetailDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }
}
