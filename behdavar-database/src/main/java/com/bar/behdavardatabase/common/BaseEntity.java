package com.bar.behdavardatabase.common;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity<I extends Serializable> {
    public static final int ALLOCATION_SIZE = 1;

    public abstract I getId();

    public abstract void setId(I id);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
