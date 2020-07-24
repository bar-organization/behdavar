package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.CatalogBusiness;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavardatabase.entity.CatalogEntity;
import com.bar.behdavardatabase.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CatalogBusinessImpl.BEAN_NAME)
public class CatalogBusinessImpl implements CatalogBusiness {
    public static final String BEAN_NAME = "catalogBusinessImpl";

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public CatalogEntity findById(Long id) {
        return catalogRepository.findById(id).orElseThrow(() -> new BusinessException("error.catalog.not.found" , id));
    }
}
