package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends AbstractRepository<ProductEntity, Long> {
}
