package com.bar.behdavardatabase.common;


import com.bar.behdavardatabase.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Configurable
public class AuditingListener {


    @PrePersist
    public void touchForCreate(BaseAuditorEntity target) {
        target.setCreatedBy(SecurityUtil.getCurrentUser());
        target.setCreatedDate(LocalDateTime.now());
    }


    @PreUpdate
    public void touchForUpdate(BaseAuditorEntity target) {
        target.setLastModifiedBy(SecurityUtil.getCurrentUser());
        target.setLastModifiedDate(LocalDateTime.now());
    }
}
