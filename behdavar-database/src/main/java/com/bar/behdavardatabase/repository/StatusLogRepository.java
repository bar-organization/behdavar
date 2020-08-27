package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.StatusLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusLogRepository extends AbstractRepository<StatusLogEntity, Long> {
}
