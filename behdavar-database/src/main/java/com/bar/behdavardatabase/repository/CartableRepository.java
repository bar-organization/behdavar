package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.CartableEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CartableRepository extends AbstractRepository<CartableEntity, Long> {
}
