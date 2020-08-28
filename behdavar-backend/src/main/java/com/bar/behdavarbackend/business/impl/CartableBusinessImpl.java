package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.CartableBusiness;
import com.bar.behdavarbackend.business.transformer.CartableTransformer;
import com.bar.behdavarbackend.dto.CartableDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.SecurityUtil;
import com.bar.behdavarbackend.util.pagination.*;
import com.bar.behdavardatabase.entity.CartableEntity;
import com.bar.behdavardatabase.repository.CartableRepository;
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

    @Override
    public CartableDto findById(Long id) {
        CartableEntity cartableEntity = cartableRepository.findById(id).orElseThrow(() -> new BusinessException("error.Cartable.not.found", id));
        return CartableTransformer.ENTITY_TO_DTO(cartableEntity, new CartableDto());
    }

    @Override
    @Transactional
    public Long save(CartableDto dto) {
        CartableEntity cartableEntity = CartableTransformer.DTO_TO_ENTITY(dto, new CartableEntity());
        cartableEntity.setId(cartableRepository.save(cartableEntity).getId());
        return cartableEntity.getId();
    }

    @Override
    @Transactional
    public void update(CartableDto dto) {
        CartableEntity cartableEntity = CartableTransformer.DTO_TO_ENTITY(dto, cartableRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.Cartable.not.found", dto.getId())));
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
}
