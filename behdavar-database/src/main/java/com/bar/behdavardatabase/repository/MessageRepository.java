package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.ContactEntity;
import com.bar.behdavardatabase.entity.MessageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends AbstractRepository<MessageEntity, Long> {
}
