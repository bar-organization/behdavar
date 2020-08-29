package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.StatusLogBusiness;
import com.bar.behdavarbackend.business.transformer.StatusLogTransformer;
import com.bar.behdavarbackend.dto.StatusLogDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.StatusLogEntity;
import com.bar.behdavardatabase.repository.StatusLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(StatusLogBusinessImpl.BEAN_NAME)
public class StatusLogBusinessImpl implements StatusLogBusiness {
    public static final String BEAN_NAME = "StatusLogBusinessImpl";

    @Autowired
    private StatusLogRepository statusLogRepository;

    @Override
    public StatusLogDto findById(Long id) {
        StatusLogEntity statusLogEntity = statusLogRepository.findById(id).orElseThrow(() -> new BusinessException("error.StatusLog.not.found", id));
        return StatusLogTransformer.ENTITY_TO_DTO(statusLogEntity, new StatusLogDto());
    }

    @Override
    @Transactional
    public Long save(StatusLogDto dto) {
        StatusLogEntity statusLogEntity = StatusLogTransformer.DTO_TO_ENTITY(dto, new StatusLogEntity());
        statusLogEntity.setId(statusLogRepository.save(statusLogEntity).getId());
        return statusLogEntity.getId();
    }

    @Override
    @Transactional
    public void update(StatusLogDto dto) {
        StatusLogEntity statusLogEntity = StatusLogTransformer.DTO_TO_ENTITY(dto, statusLogRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.StatusLog.not.found", dto.getId())));
        statusLogRepository.save(statusLogEntity);
    }

    @Override
    public void delete(Long id) {
        statusLogRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(statusLogRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<StatusLogEntity> data = (List<StatusLogEntity>) pagingResponse.getData();
            List<StatusLogDto> output = new ArrayList<>();
            data.forEach(e -> output.add(StatusLogTransformer.ENTITY_TO_DTO(e, new StatusLogDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }

}
