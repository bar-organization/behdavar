package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.GuarantorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuarantorRepository extends AbstractRepository<GuarantorEntity, Long> {

    List<GuarantorEntity> findAllByContractId(Long contractId);
}
