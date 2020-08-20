package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.AttachmentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends AbstractRepository<AttachmentEntity, Long> {
}
