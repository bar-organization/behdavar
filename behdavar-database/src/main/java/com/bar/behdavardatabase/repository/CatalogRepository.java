package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.CatalogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends AbstractRepository<CatalogEntity, Long> {
}
