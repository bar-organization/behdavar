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
    private BankRepository bankRepository;

    @Override
    public BankBranchEntity findById(Long id) {
        return bankRepository.findById(id).orElseThrow(() -> new BusinessException("error.Bank.not.found", id));
    }

    @Override
    public Long save(BankDto dto) {
        BankBranchEntity bankBranchEntity = BankTransformer.dtoToEntity(dto, new BankBranchEntity());
        return bankRepository.save(bankBranchEntity).getId();
    }

    @Override
    public void update(BankDto dto) {
        BankBranchEntity bankBranchEntity = BankTransformer.dtoToEntity(dto, findById(dto.getId()));
        bankRepository.save(bankBranchEntity);
    }

    @Override
    public void delete(Long id) {
        bankRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<BankBranchEntity, Long> executor = new PagingExecutor<>(bankRepository, pagingRequest);
        return executor.execute();
    }
}
