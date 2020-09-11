package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.UserBusiness;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.config.StartupPreparation;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBusinessImpl implements UserBusiness {

    private final UserRepository userRepository;

    @Autowired
    public UserBusinessImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findByUserName(String username) throws BusinessException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException("Username not found"));
        return UserTransformer.ENTITY_TO_DTO(userEntity, new UserDto(), UserEntity.ROLES);
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(UserEntity -> UserTransformer.ENTITY_TO_DTO(UserEntity, new UserDto()))
                .orElseThrow(() -> new BusinessException("error.User.not.found", id));
    }

    @Override
    public Long save(UserDto dto) {
        UserEntity userEntity = UserTransformer.DTO_TO_ENTITY(dto, new UserEntity());
        return userRepository.save(userEntity).getId();
    }

    @Override
    public void update(UserDto dto) {
        if (dto.getUsername().equals(StartupPreparation.SUPERVISOR_USER)) {
            new BusinessException("error.invalid.operation");
        }
        UserEntity userEntity = UserTransformer.DTO_TO_ENTITY(dto, userRepository
                .findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.User.not.found", dto.getId())));
        userRepository.save(userEntity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<UserEntity, Long> executor = new PagingExecutor(userRepository, pagingRequest);
        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<UserDto> UserDtos = new ArrayList<>();
            ((List<UserEntity>) pagingResponse.getData()).forEach(e ->
                    UserDtos.add(UserTransformer.ENTITY_TO_DTO(e, new UserDto()))
            );
            pagingResponse.setData(UserDtos);
        }
        return pagingResponse;
    }
}
