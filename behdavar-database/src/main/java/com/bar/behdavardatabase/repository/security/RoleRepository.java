package com.bar.behdavardatabase.repository.security;

import com.bar.behdavardatabase.entity.security.RoleEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractRepository<RoleEntity, Long> {
}
