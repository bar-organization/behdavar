package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends AbstractRepository<AddressEntity, Long> {
}
