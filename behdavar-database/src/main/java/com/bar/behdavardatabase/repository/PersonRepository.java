package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.PersonEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends AbstractRepository<PersonEntity, Long> {
}
