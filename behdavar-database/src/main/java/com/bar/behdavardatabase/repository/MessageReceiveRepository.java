package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.MessageReceiverEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReceiveRepository extends AbstractRepository<MessageReceiverEntity, Long> {
}

