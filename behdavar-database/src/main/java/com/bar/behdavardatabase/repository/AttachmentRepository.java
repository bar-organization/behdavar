package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.AttachmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends AbstractRepository<AttachmentEntity, Long> {
    List<AttachmentEntity> findAllByContractId(Long contractId);
}
