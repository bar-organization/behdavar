package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.BankBranchEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends AbstractRepository<BankBranchEntity, Long> {
}
