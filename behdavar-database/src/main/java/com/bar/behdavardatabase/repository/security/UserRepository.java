package com.bar.behdavardatabase.repository.security;

import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
