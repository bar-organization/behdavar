package com.bar.behdavardatabase.repository.security;

import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity, Long> {
}
