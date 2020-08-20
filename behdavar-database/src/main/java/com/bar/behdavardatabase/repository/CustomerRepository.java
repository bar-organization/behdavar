package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends AbstractRepository<CustomerEntity, Long> {
}
