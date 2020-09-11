package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.UserLogBusiness;
import com.bar.behdavarbackend.business.transformer.UserLogTransformer;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavarbackend.dto.UserLogDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.UserLogEntity;
import com.bar.behdavardatabase.repository.UserLogRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service(UserLogBusinessImpl.BEAN_NAME)
public class UserLogBusinessImpl implements UserLogBusiness {
    public static final String BEAN_NAME = "UserLogBusinessImpl";

    @Autowired
    private UserLogRepository userLogRepository;

    @Override
    public UserLogEntity findById(Long id) {
        return userLogRepository.findById(id).orElseThrow(() -> new BusinessException("error.UserLog.not.found", id));
    }

    @Override
    public Long save(UserLogDto dto) {
        UserLogEntity userLogEntity = UserLogTransformer.dtoToEntity(dto, new UserLogEntity());
        return userLogRepository.save(userLogEntity).getId();
    }

    @Override
    public void update(UserLogDto dto) {
        UserLogEntity userLogEntity = UserLogTransformer.dtoToEntity(dto, findById(dto.getId()));
        userLogRepository.save(userLogEntity);
    }

    @Override
    public void delete(Long id) {
        userLogRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<UserLogEntity, Long> executor = new PagingExecutor<>(userLogRepository, pagingRequest);
        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<UserLogEntity> data = (List<UserLogEntity>) pagingResponse.getData();
            List<UserLogDto> output = new ArrayList<>();
            data.forEach(e -> output.add(UserLogTransformer.entityToDto(e, new UserLogDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }

    @Override
    public void writeLoginLog(UserDto userDto) {
        UserLogDto dto = new UserLogDto();
        dto.setUser(userDto);
        dto.setLastLogin(LocalDateTime.now());
        save(dto);
    }

    @Override
    public LocalDateTime getLastLogin() {
        return userLogRepository.findLastLogin(SecurityUtil.getCurrentUserId());
    }
}
