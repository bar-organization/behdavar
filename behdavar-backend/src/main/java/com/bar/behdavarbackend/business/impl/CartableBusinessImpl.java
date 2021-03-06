package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.*;
import com.bar.behdavarbackend.business.transformer.CartableTransformer;
import com.bar.behdavarbackend.business.transformer.ContractTransformer;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.dto.*;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.*;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavardatabase.entity.CartableEntity;
import com.bar.behdavardatabase.entity.ContractEntity;
import com.bar.behdavardatabase.entity.CustomerEntity;
import com.bar.behdavardatabase.repository.CartableRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service(CartableBusinessImpl.BEAN_NAME)
public class CartableBusinessImpl implements CartableBusiness {
    public static final String BEAN_NAME = "CartableBusinessImpl";

    @Autowired
    private CartableRepository cartableRepository;

    @Autowired
    private StatusLogBusiness statusLogBusiness;

    @Autowired
    private UserLogBusiness userLogBusiness;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private UserAmountBusiness userAmountBusiness;


    @Override
    public CartableDto findById(Long id) {
        CartableEntity cartableEntity = cartableRepository.findById(id).orElseThrow(() -> new BusinessException("error.Cartable.not.found", id));
        return CartableTransformer.entityToDto(cartableEntity, new CartableDto());
    }

    @Override
    @Transactional
    public Long save(CartableDto dto) {
        CartableEntity cartableEntity = CartableTransformer.dtoToEntity(dto, new CartableEntity());
        cartableEntity.setActive(Boolean.TRUE);
        cartableEntity.setId(cartableRepository.save(cartableEntity).getId());
        return cartableEntity.getId();
    }

    @Override
    @Transactional
    public void update(CartableDto dto) {
        CartableEntity cartableEntity = CartableTransformer.dtoToEntity(dto, cartableRepository.findById(dto.getId())
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
        pagingRequest.getFilters().add(new SearchCriteria(CartableEntity.ACTIVE, true, SearchOperation.EQUAL));
        return getPagingResponse(pagingRequest);
    }

    @Override
    public PagingResponse findPagingAll(PagingRequest pagingRequest) {
        pagingRequest.getFilters().stream()
                .filter(searchCriteria -> searchCriteria.getKey().equals("contract.contractStatus"))
                .findFirst()
                .ifPresent(searchCriteria ->
                        searchCriteria.setValue(ContractStatus.getByName(searchCriteria.getValue().toString())));
        pagingRequest.getFilters().add(new SearchCriteria(CartableEntity.ACTIVE, true, SearchOperation.EQUAL));
        return getPagingResponse(pagingRequest);
    }

    @Override
    public PagingResponse findPagingDocumentFlow(PagingRequest pagingRequest) {
        if (pagingRequest == null) {
            pagingRequest = new PagingRequest();
        }

        List<DocumentFlowDto> resultList = new ArrayList<>();
        pagingRequest.setSort(new SortOperation(Sort.Direction.ASC, "createdDate"));

        PagingResponse pagingAll = this.getPagingResponse(pagingRequest);
        List<CartableDto> cartableDtoPage = (List<CartableDto>) pagingAll.getData();
        Iterator<CartableDto> iterator = cartableDtoPage.iterator();

        while (iterator.hasNext()) {
            CartableDto current = iterator.next();

            DocumentFlowDto flowDto = new DocumentFlowDto();
            flowDto.setPreviousReceiver(current.getReceiver());
            flowDto.setSender(current.getSender());
            flowDto.setCreatedDate(current.getCreatedDate());
            resultList.add(flowDto);
        }
        pagingAll.setData(resultList);
        return pagingAll;
    }

    private PagingResponse getPagingResponse(PagingRequest pagingRequest) {
        pagingRequest.getFilters().stream()
                .filter(searchCriteria -> searchCriteria.getKey().equals("contract.contractStatus"))
                .findFirst()
                .ifPresent(searchCriteria ->
                        searchCriteria.setValue(ContractStatus.getByName(searchCriteria.getValue().toString())));
        PagingExecutor<CartableEntity, Long> executor = new PagingExecutor<>(cartableRepository, pagingRequest);
        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<CartableEntity> data = (List<CartableEntity>) pagingResponse.getData();
            List<CartableDto> output = new ArrayList<>();
            data.forEach(e -> output.add(CartableTransformer.entityToDto(e, new CartableDto(),
                    CartableEntity.CONTRACT, ContractEntity.CUSTOMERS, CustomerEntity.PERSON)));
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
        newEntity.setContract(ContractTransformer.createEntityForRelation(dto.getContractId()));
        newEntity.setReceiver(UserTransformer.createEntityForRelation(dto.getAssigneeId()));
        newEntity.setSender(UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId()));
        newEntity.setActive(Boolean.TRUE);
        cartableRepository.save(newEntity);

        StatusLogDto statusLogDto = new StatusLogDto();
        statusLogDto.setStatus(dto.getStatus());
        statusLogDto.setContract(ContractTransformer.createDtoForRelation(dto.getContractId()));
        statusLogDto.setUser(UserTransformer.createDtoForRelation(SecurityUtil.getCurrentUserId()));
        statusLogBusiness.save(statusLogDto);


    }

    @Override
    public UserInfoDto getUserInfo() {
        UserInfoDto userInfoDto = new UserInfoDto();
        Long currentUserId = SecurityUtil.getCurrentUserId();
        userInfoDto.setUser(userBusiness.findById(currentUserId));
        userInfoDto.setActiveCount(cartableRepository.findUserActiveCount(currentUserId));
        userInfoDto.setLastLogin(userLogBusiness.getLastLogin());
        userInfoDto.setUserAmount(userAmountBusiness.findByUserId(currentUserId));

        return userInfoDto;
    }
}
