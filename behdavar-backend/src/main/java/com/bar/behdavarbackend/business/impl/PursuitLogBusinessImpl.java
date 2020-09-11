package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.PursuitLogBusiness;
import com.bar.behdavarbackend.business.transformer.PursuitLogTransformer;
import com.bar.behdavarbackend.dto.PursuitLogDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.PursuitEntity;
import com.bar.behdavardatabase.entity.PursuitLogEntity;
import com.bar.behdavardatabase.repository.PursuitLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(PursuitLogBusinessImpl.BEAN_NAME)
public class PursuitLogBusinessImpl implements PursuitLogBusiness {
    public static final String BEAN_NAME = "PursuitLogBusinessImpl";

    @Autowired
    private PursuitLogRepository pursuitLogRepository;

    @Override
    public PursuitLogDto findById(Long id) {
        PursuitLogEntity pursuitLogEntity = pursuitLogRepository.findById(id).orElseThrow(() -> new BusinessException("error.PursuitLog.not.found", id));
        return PursuitLogTransformer.entityToDto(pursuitLogEntity, new PursuitLogDto());
    }

    @Override
    public Long save(PursuitEntity entity) {
        PursuitLogEntity pursuitLogEntity = PursuitLogTransformer.pursuitEntityToLogEntity(entity, new PursuitLogEntity());
        return pursuitLogRepository.save(pursuitLogEntity).getId();
    }


    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<PursuitLogEntity, Long> executor = new PagingExecutor<>(pursuitLogRepository, pagingRequest);
        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<PursuitLogEntity> data = (List<PursuitLogEntity>) pagingResponse.getData();
            List<PursuitLogDto> output = new ArrayList<>();
            data.forEach(e -> output.add(PursuitLogTransformer.entityToDto(e, new PursuitLogDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }
}
