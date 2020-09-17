package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.LendingEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingRepository extends AbstractRepository<LendingEntity, Long> {
    LendingEntity findByContractId(Long contractNumber);
}
