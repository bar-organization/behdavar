package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.CartableEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartableRepository extends AbstractRepository<CartableEntity, Long> {
    Optional<CartableEntity> findByContractIdAndActive(Long contractId, Boolean active);

    @Query("select count (c.id) from CartableEntity c where c.active = true and c.receiver.id = :userId")
    Long findUserActiveCount(@Param("userId") Long userId);
}
