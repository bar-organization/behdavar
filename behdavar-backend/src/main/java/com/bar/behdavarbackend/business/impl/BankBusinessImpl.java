package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.BankBusiness;
import com.bar.behdavarbackend.business.transformer.BankTransformer;
import com.bar.behdavarbackend.dto.BankDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.BankBranchEntity;
import com.bar.behdavardatabase.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(BankBusinessImpl.BEAN_NAME)
public class BankBusinessImpl implements BankBusiness {
    public static final String BEAN_NAME = "BankBusinessImpl";

    @Autowired
    private BankRepository BankRepository;

    @Override
    public BankBranchEntity findById(Long id) {
        return BankRepository.findById(id).orElseThrow(() -> new BusinessException("error.Bank.not.found", id));
    }

    @Override
    public Long save(BankDto dto) {
        BankBranchEntity BankBranchEntity = BankTransformer.DTO_TO_ENTITY(dto, new BankBranchEntity());
        return BankRepository.save(BankBranchEntity).getId();
    }

    @Override
    public void update(BankDto dto) {
        BankBranchEntity BankBranchEntity = BankTransformer.DTO_TO_ENTITY(dto, findById(dto.getId()));
        BankRepository.save(BankBranchEntity);
    }

    @Override
    public void delete(Long id) {
        BankRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(BankRepository, pagingRequest);
        return executor.execute();
    }
}
