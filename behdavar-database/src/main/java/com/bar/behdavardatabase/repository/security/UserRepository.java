package com.bar.behdavardatabase.repository.security;

import com.bar.behdavardatabase.entity.security.UserEntity;
import com.bar.behdavardatabase.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity, Long> {

    @Query("select u from UserEntity u inner join u.person p where u.username <> :#{T(com.bar.behdavarbackend.config.StartupPreparation).SUPERVISOR_USER} and (concat(p.firstName,' ', p.lastName) like %:suggest%  or u.username like %:suggest%) ")
    List<UserEntity> findSuggestion(@Param("suggest") String suggest);

    Optional<UserEntity> findByUsername(String username);

    UserEntity findByCode(Long code);
}
