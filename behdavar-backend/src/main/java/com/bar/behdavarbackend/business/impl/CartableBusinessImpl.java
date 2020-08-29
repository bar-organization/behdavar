package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.CartableBusiness;
import com.bar.behdavarbackend.business.api.StatusLogBusiness;
import com.bar.behdavarbackend.business.transformer.CartableTransformer;
import com.bar.behdavarbackend.business.transformer.ContractTransformer;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.dto.AssignContractDto;
import com.bar.behdavarbackend.dto.CartableDto;
import com.bar.behdavarbackend.dto.StatusLogDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.*;
import com.bar.behdavardatabase.entity.CartableEntity;
import com.bar.behdavardatabase.repository.CartableRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(CartableBusinessImpl.BEAN_NAME)
public class CartableBusinessImpl implements CartableBusiness {
    public static final String BEAN_NAME = "CartableBusinessImpl";

    @Autowired
    private CartableRepository cartableRepository;

    @Autowired
    private StatusLogBusiness statusLogBusiness;

    @Override
    public CartableDto findById(Long id) {
        CartableEntity cartableEntity = cartableRepository.findById(id).orElseThrow(() -> new BusinessException("error.Cartable.not.found", id));
        return CartableTransformer.ENTITY_TO_DTO(cartableEntity, new CartableDto());
    }

    @Override
    @Transactional
    public Long save(CartableDto dto) {
        CartableEntity cartableEntity = CartableTransformer.DTO_TO_ENTITY(dto, new CartableEntity());
        cartableEntity.setActive(Boolean.TRUE);
        cartableEntity.setId(cartableRepository.save(cartableEntity).getId());
        return cartableEntity.getId();
    }

    @Override
    @Transactional
    public void update(CartableDto dto) {
        CartableEntity cartableEntity = CartableTransformer.DTO_TO_ENTITY(dto, cartableRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.Cartable.not.found", dto.getId())));
        cartableEntity.setActive(Boolean.TRUE);
        cartableRepository.save(cartableEntity);
    }

    @Override
    public void delete(Long id) {
        cartableRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        pagingRequest.getFilters().add(new SearchCriteria(CartableEntity.RECEIVER, SecurityUtil.getCurrentUserId(), SearchOperation.EQUAL));
        PagingExecutor executor = new PagingExecutor(cartableRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<CartableEntity> data = (List<CartableEntity>) pagingResponse.getData();
            List<CartableDto> output = new ArrayList<>();
            data.forEach(e -> output.add(CartableTransformer.ENTITY_TO_DTO(e, new CartableDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }

    @Transactional
    @Override
    public void assignContract(AssignContractDto dto) {
        cartableRepository.findByContractIdAndActive(dto.getContractId(), Boolean.TRUE)
                .ifPresent(cartableEntity -> {
                    cartableEntity.setActive(false);
                    cartableRepository.save(cartableEntity);
                });
        CartableEntity newEntity = new CartableEntity();
        newEntity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContractId()));
        newEntity.setReceiver(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getAssigneeId()));
        newEntity.setSender(UserTransformer.CREATE_ENTITY_FOR_RELATION(SecurityUtil.getCurrentUserId()));
        newEntity.setActive(Boolean.TRUE);
        cartableRepository.save(newEntity);

        StatusLogDto statusLogDto = new StatusLogDto();
        statusLogDto.setStatus(dto.getStatus());
        statusLogDto.setContract(ContractTransformer.CREATE_DTO_FOR_RELATION(dto.getContractId()));
        statusLogDto.setUser(UserTransformer.CREATE_DTO_FOR_RELATION(dto.getAssigneeId()));
        statusLogBusiness.save(statusLogDto);


    }
}
