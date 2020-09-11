package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.UserAmountBusiness;
import com.bar.behdavarbackend.business.transformer.UserAmountTransformer;
import com.bar.behdavarbackend.dto.UserAmountDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.UserAmountEntity;
import com.bar.behdavardatabase.repository.UserAmountRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(UserAmountBusinessImpl.BEAN_NAME)
public class UserAmountBusinessImpl implements UserAmountBusiness {
    public static final String BEAN_NAME = "UserAmountBusinessImpl";

    @Autowired
    private UserAmountRepository userAmountRepository;

    @Override
    public UserAmountDto findById(Long id) {
        UserAmountEntity userAmountEntity = userAmountRepository.findById(id).orElseThrow(() -> new BusinessException("error.UserAmount.not.found", id));
        return UserAmountTransformer.entityToDto(userAmountEntity, new UserAmountDto());
    }

    @Override
    @Transactional
    public Long save(UserAmountDto dto) {
        UserAmountEntity userAmountEntity = UserAmountTransformer.dtoToEntity(dto, new UserAmountEntity());
        userAmountEntity.setId(userAmountRepository.save(userAmountEntity).getId());
        return userAmountEntity.getId();
    }

    @Override
    @Transactional
    public void update(UserAmountDto dto) {
        UserAmountEntity userAmountEntity = UserAmountTransformer.dtoToEntity(dto, userAmountRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.UserAmount.not.found", dto.getId())));
        userAmountRepository.save(userAmountEntity);
    }

    @Override
    public void delete(Long id) {
        userAmountRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<UserAmountEntity, Long> executor = new PagingExecutor<>(userAmountRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<UserAmountEntity> data = (List<UserAmountEntity>) pagingResponse.getData();
            List<UserAmountDto> output = new ArrayList<>();
            data.forEach(e -> output.add(UserAmountTransformer.entityToDto(e, new UserAmountDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }

    @Override
    public void increaseReceiveAmount(BigDecimal amount) {
        Optional<UserAmountEntity> userAmountEntity = userAmountRepository.findByUserId(SecurityUtil.getCurrentUserId());
        userAmountEntity.ifPresent(e -> {
            e.setReceiveAmount(e.getReceiveAmount() != null ? e.getReceiveAmount().add(amount) : amount);
            userAmountRepository.save(e);
        });
    }

    @Override
    public UserAmountDto findByUserId(Long userId) {
        return userAmountRepository.findByUserId(userId)
                .map(userAmountEntity -> UserAmountTransformer.entityToDto(userAmountEntity, new UserAmountDto()))
                .orElse(null);
    }

}
