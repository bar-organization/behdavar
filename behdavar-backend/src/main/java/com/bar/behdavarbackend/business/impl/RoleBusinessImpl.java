package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.RoleBusiness;
import com.bar.behdavarbackend.business.transformer.RoleTransformer;
import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.security.RoleEntity;
import com.bar.behdavardatabase.repository.security.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(RoleBusinessImpl.BEAN_NAME)
public class RoleBusinessImpl implements RoleBusiness {
    public static final String BEAN_NAME = "RoleBusinessImpl";

    @Autowired
    private RoleRepository repository;

    @Override
    public RoleDto findById(Long id) {
        return repository.findById(id)
                .map(RoleEntity -> RoleTransformer.ENTITY_TO_DTO(RoleEntity, new RoleDto(), RoleEntity.PRIVILEGES))
                .orElseThrow(() -> new BusinessException("error.Role.not.found", id));
    }

    @Override
    public Long save(RoleDto dto) {
        RoleEntity roleEntity = RoleTransformer.DTO_TO_ENTITY(dto, new RoleEntity());
        return repository.save(roleEntity).getId();
    }

    @Override
    public void update(RoleDto dto) {
        RoleEntity roleEntity = RoleTransformer.DTO_TO_ENTITY(dto, repository
                .findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.Role.not.found", dto.getId())));
        repository.save(roleEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<RoleEntity, Long> executor = new PagingExecutor(repository, pagingRequest);
        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<RoleDto> RoleDtos = new ArrayList<>();
            ((List<RoleEntity>) pagingResponse.getData()).forEach(e ->
                    RoleDtos.add(RoleTransformer.ENTITY_TO_DTO(e, new RoleDto()))
            );
            pagingResponse.setData(RoleDtos);
        }
        return pagingResponse;
    }
}
