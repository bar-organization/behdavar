package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.RoleBusiness;
import com.bar.behdavarbackend.business.transformer.RoleTransformer;
import com.bar.behdavarbackend.config.StartupPreparation;
import com.bar.behdavarbackend.dto.RoleDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.security.RoleEntity;
import com.bar.behdavardatabase.repository.security.RoleRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service(RoleBusinessImpl.BEAN_NAME)
public class RoleBusinessImpl implements RoleBusiness {
    public static final String BEAN_NAME = "RoleBusinessImpl";

    @Autowired
    private RoleRepository repository;

    @Override
    public RoleDto findById(Long id) {
        return repository.findById(id)
                .map(entity -> RoleTransformer.entityToDto(entity, new RoleDto(), RoleEntity.PRIVILEGES))
                .orElseThrow(() -> new BusinessException("error.Role.not.found", id));
    }

    @Override
    public Long save(RoleDto dto) {
        RoleEntity roleEntity = RoleTransformer.dtoToEntity(dto, new RoleEntity());
        return repository.save(roleEntity).getId();
    }

    @Override
    public void update(RoleDto dto) {
        RoleEntity roleEntity = RoleTransformer.dtoToEntity(dto, repository
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
        PagingExecutor<RoleEntity, Long> executor = new PagingExecutor<>(repository, pagingRequest);
        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<RoleDto> roleDtos = new ArrayList<>();
            ((List<RoleEntity>) pagingResponse.getData()).forEach(e ->
                    roleDtos.add(RoleTransformer.entityToDto(e, new RoleDto()))
            );
            // TODO must filter this with query
            pagingResponse.setData(this.filterSupervisorRole(roleDtos));
        }
        return pagingResponse;
    }

    @Override
    public List<RoleDto> findSuggestion(String suggest) {
        List<RoleEntity> roleEntities = repository.findAllByNameOrTitle(suggest, PageRequest.of(0, 10)).getContent();
        List<RoleDto> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleEntities)) {
            roleEntities.forEach(e -> result.add(RoleTransformer.entityToDto(e, new RoleDto())));
        }
        // TODO must filter this with query
        return this.filterSupervisorRole(result);
    }

    private List<RoleDto> filterSupervisorRole(List<RoleDto> roleDtos) {
        if (!SecurityUtil.getCurrentUser().equals(StartupPreparation.SUPERVISOR_USER)) {
            return roleDtos.stream().filter(role -> !role.getRoleName().equals(StartupPreparation.SUPERVISOR_ROLE)).collect(Collectors.toList());
        }
        return roleDtos;
    }

}
