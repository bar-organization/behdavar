package com.bar.behdavardatabase.repository.security;

import com.bar.behdavardatabase.entity.security.PrivilegeEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends AbstractRepository<PrivilegeEntity, Long> {
}
