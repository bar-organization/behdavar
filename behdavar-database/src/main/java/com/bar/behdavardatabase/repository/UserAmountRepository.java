package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.UserAmountEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAmountRepository extends AbstractRepository<UserAmountEntity, Long> {

    Optional<UserAmountEntity> findByUserId(Long userId);
}
