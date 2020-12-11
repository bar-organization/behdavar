package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.ContactEntity;
import com.bar.behdavardatabase.entity.MessageEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends AbstractRepository<MessageEntity, Long> {
    @Query(" select  m from MessageEntity m inner join m.receivers r where r.receiver.id = :userId  order by m.createdDate desc , r.isRead asc ")
    List<MessageEntity> findUserMessages(Long userId);
}
