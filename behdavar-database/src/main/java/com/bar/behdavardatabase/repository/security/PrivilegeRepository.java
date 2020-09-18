package com.bar.behdavardatabase.repository.security;

import com.bar.behdavardatabase.entity.security.PrivilegeEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends AbstractRepository<PrivilegeEntity, Long> {
    @Query("select p from PrivilegeEntity p where lower(p.name) like concat('%',lower(trim(?1)),'%')")
    Page<PrivilegeEntity> findAllByName(String name, Pageable pageable);
}
