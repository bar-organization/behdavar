package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends AbstractRepository<CustomerEntity, Long> {

    List<CustomerEntity> findByContractId(Long contractId);
}
