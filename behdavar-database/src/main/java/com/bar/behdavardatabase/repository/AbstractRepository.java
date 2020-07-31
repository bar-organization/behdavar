package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public interface AbstractRepository<T extends BaseEntity<ID>, ID extends Serializable> extends PagingAndSortingRepository<T, ID>,
        JpaSpecificationExecutor<T> {
}
