package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.ContractBusiness;
import com.bar.behdavarbackend.business.transformer.ContractTransformer;
import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavardatabase.entity.ContractEntity;
import com.bar.behdavardatabase.entity.CustomerEntity;
import com.bar.behdavardatabase.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void updateStatus(Long contractId, ContractStatus newStatus) {
        ContractEntity contractEntity = contractRepository.findById(contractId)
                .orElseThrow(() -> new BusinessException("error.contract.not.found", contractId));

        contractEntity.setContractStatus(newStatus);

        contractRepository.save(contractEntity);
    }

}
