package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.PrivilegeBusiness;
import com.bar.behdavarbackend.business.transformer.PrivilegeTransformer;
import com.bar.behdavarbackend.dto.PrivilegeDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.security.PrivilegeEntity;
import com.bar.behdavardatabase.repository.security.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(PrivilegeBusinessImpl.BEAN_NAME)
public class PrivilegeBusinessImpl implements PrivilegeBusiness {
    public static final String BEAN_NAME = "PrivilegeBusinessImpl";

    @Autowired
    private PrivilegeRepository repository;

    @Override
    public PrivilegeDto findById(Long id) {
        return repository.findById(id)
                .map(entity -> PrivilegeTransformer.entityToDto(entity, new PrivilegeDto()))
                .orElseThrow(() -> new BusinessException("error.Privilege.not.found", id));
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<PrivilegeEntity, Long> executor = new PagingExecutor<>(repository, pagingRequest);
        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<PrivilegeDto> privilegeDtos = new ArrayList<>();
            ((List<PrivilegeEntity>) pagingResponse.getData()).forEach(e ->
                    privilegeDtos.add(PrivilegeTransformer.entityToDto(e, new PrivilegeDto()))
            );
            pagingResponse.setData(privilegeDtos);
        }
        return pagingResponse;
    }
}
