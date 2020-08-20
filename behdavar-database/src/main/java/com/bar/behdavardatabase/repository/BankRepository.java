package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.BankEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends AbstractRepository<BankEntity, Long> {
}
