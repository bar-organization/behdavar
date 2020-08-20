package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.ContactEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends AbstractRepository<ContactEntity, Long> {
}
