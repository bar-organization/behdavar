package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.MessageEntity;
import com.bar.behdavardatabase.entity.MessageReceiverEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageReceiveRepository extends AbstractRepository<MessageReceiverEntity, Long> {

}

