package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.UserLogEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserLogRepository extends AbstractRepository<UserLogEntity, Long> {

    @Query("SELECT max(u.lastLogin) from UserLogEntity u where u.user.id = :userId")
    LocalDateTime findLastLogin(@Param("userId") Long userId);
}
