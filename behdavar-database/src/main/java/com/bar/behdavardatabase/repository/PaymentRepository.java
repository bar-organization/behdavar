package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.PaymentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends AbstractRepository<PaymentEntity, Long> {
}
