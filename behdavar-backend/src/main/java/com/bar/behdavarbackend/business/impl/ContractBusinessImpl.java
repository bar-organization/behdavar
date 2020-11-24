package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.*;
import com.bar.behdavarbackend.business.transformer.CartableTransformer;
import com.bar.behdavarbackend.business.transformer.ContractTransformer;
import com.bar.behdavarbackend.business.transformer.UserTransformer;
import com.bar.behdavarbackend.dto.*;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.*;
import com.bar.behdavardatabase.entity.CartableEntity;
import com.bar.behdavardatabase.entity.ContractEntity;
import com.bar.behdavardatabase.entity.CustomerEntity;
import com.bar.behdavardatabase.repository.CartableRepository;
import com.bar.behdavardatabase.repository.ContractRepository;
import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(ContractBusinessImpl.BEAN_NAME)
public class ContractBusinessImpl implements ContractBusiness {
    public static final String BEAN_NAME = "ContractBusinessImpl";

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public ContractDto findById(Long id) {
        ContractEntity contractEntity = contractRepository.findById(id).orElseThrow(() -> new BusinessException("error.Contract.not.found", id));
        return ContractTransformer.entityToDto(contractEntity, new ContractDto(), ContractEntity.CUSTOMERS, CustomerEntity.PERSON);
    }


}
