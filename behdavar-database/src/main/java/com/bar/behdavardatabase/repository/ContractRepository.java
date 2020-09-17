package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.ContractEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends AbstractRepository<ContractEntity, Long> {

    ContractEntity findByContractNumber(String contractNumber);
}
