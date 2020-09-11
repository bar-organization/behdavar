package com.bar.behdavardatabase.repository.security;

import com.bar.behdavardatabase.entity.security.RoleEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String string);


    @Query("select r from RoleEntity r where lower(r.name) like concat('%',lower(trim(?1)),'%') or r.title like concat('%',trim(?1),'%' )")
    Page<RoleEntity> findAllByNameOrTitle(String name, Pageable pageable);
}
