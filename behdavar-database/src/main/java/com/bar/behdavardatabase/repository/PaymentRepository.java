package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.PaymentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends AbstractRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByContractId(Long contractId);
}
