package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.CartableEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartableRepository extends AbstractRepository<CartableEntity, Long> {
    Optional<CartableEntity> findByContractIdAndActive(Long contractId, Boolean active);
}
