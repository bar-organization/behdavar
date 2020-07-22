package com.bar.behdavardatabase.common;


import com.bar.behdavardatabase.constant.common.BaseAuditorConstant;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditorEntity<U, I extends Serializable> extends BaseEntity<I> {

    @CreatedBy
    @Column(name = BaseAuditorConstant.CREATED_BY)
    private U createdBy;

    @CreatedDate
    @Column(name = BaseAuditorConstant.CREATED_DATE)
    private LocalDateTime createdDate;


    @LastModifiedBy
    @Column(name = BaseAuditorConstant.LAST_MODIFIED_BY)
    private U lastModifiedBy;


    @LastModifiedDate
    @Column(name = BaseAuditorConstant.LAST_MODIFIED_DATE)
    private LocalDateTime lastModifiedDate;


    @Version
    @Column(name = BaseAuditorConstant.VERSION)
    private Long version;

    protected BaseAuditorEntity() {
    }

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public U getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
