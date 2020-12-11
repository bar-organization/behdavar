package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.MessageAttachmentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageAttachmentRepository extends AbstractRepository<MessageAttachmentEntity, Long> {
}
