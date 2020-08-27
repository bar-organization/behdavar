package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.PursuitEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PursuitRepository extends AbstractRepository<PursuitEntity, Long> {
    PursuitEntity findFirstByContractId(Long id);
}
