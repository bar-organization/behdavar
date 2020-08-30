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

import java.util.ArrayList;
import java.util.List;

@Service(CatalogBusinessImpl.BEAN_NAME)
public class CatalogBusinessImpl implements CatalogBusiness {
    public static final String BEAN_NAME = "catalogBusinessImpl";

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public CatalogDto findById(Long id) {
        CatalogEntity entity = catalogRepository.findById(id)
                .orElseThrow(() -> new BusinessException("error.catalog.not.found", id));
        return CatalogTransformer.ENTITY_TO_DTO(entity, new CatalogDto());
    }

    @Override
    public Long save(CatalogDto dto) {
        CatalogEntity catalogEntity = CatalogTransformer.DTO_TO_ENTITY(dto, new CatalogEntity());
        return catalogRepository.save(catalogEntity).getId();
    }

    @Override
    public void update(CatalogDto dto) {
        CatalogEntity catalogEntity = CatalogTransformer.DTO_TO_ENTITY(dto, catalogRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.catalog.not.found", dto.getId())));
        catalogRepository.save(catalogEntity);
    }

    @Override
    public void delete(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<CatalogEntity, Long> executor = new PagingExecutor<>(catalogRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<CatalogEntity> data = (List<CatalogEntity>) pagingResponse.getData();
            List<CatalogDto> output = new ArrayList<>();
            data.forEach(e -> output.add(CatalogTransformer.ENTITY_TO_DTO(e, new CatalogDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }
}
