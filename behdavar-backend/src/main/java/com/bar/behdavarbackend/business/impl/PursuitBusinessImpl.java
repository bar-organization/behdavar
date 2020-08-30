package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.PursuitBusiness;
import com.bar.behdavarbackend.business.api.PursuitLogBusiness;
import com.bar.behdavarbackend.business.transformer.PursuitTransformer;
import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.PursuitEntity;
import com.bar.behdavardatabase.repository.PursuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(PursuitBusinessImpl.BEAN_NAME)
public class PursuitBusinessImpl implements PursuitBusiness {
    public static final String BEAN_NAME = "PursuitBusinessImpl";
    @Autowired
    PursuitLogBusiness pursuitLogBusiness;
    @Autowired
    private PursuitRepository pursuitRepository;

    @Override
    public PursuitDto findById(Long id) {
        PursuitEntity pursuitEntity = pursuitRepository.findById(id).orElseThrow(() -> new BusinessException("error.Pursuit.not.found", id));
        return PursuitTransformer.ENTITY_TO_DTO(pursuitEntity, new PursuitDto());
    }

    @Override
    @Transactional
    public Long save(PursuitDto dto) {
        PursuitEntity pursuitEntity = PursuitTransformer.DTO_TO_ENTITY(dto, new PursuitEntity());
        pursuitEntity.setId(pursuitRepository.save(pursuitEntity).getId());
        pursuitLogBusiness.save(pursuitEntity);
        return pursuitEntity.getId();
    }

    @Override
    @Transactional
    public void update(PursuitDto dto) {
        PursuitEntity pursuitEntity = PursuitTransformer.DTO_TO_ENTITY(dto, pursuitRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.Pursuit.not.found", dto.getId())));
        pursuitRepository.save(pursuitEntity);
        pursuitLogBusiness.save(pursuitEntity);
    }

    @Override
    public void delete(Long id) {
        pursuitRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(pursuitRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<PursuitEntity> data = (List<PursuitEntity>) pagingResponse.getData();
            List<PursuitDto> output = new ArrayList<>();
            data.forEach(e -> output.add(PursuitTransformer.ENTITY_TO_DTO(e, new PursuitDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }

    @Override
    public PursuitDto findByContractId(Long id) {
        PursuitEntity pursuitEntity = pursuitRepository.findFirstByContractId(id);
        return pursuitEntity != null ? PursuitTransformer.ENTITY_TO_DTO(pursuitEntity, new PursuitDto()) : null;
    }
}
