package com.bar.behdavarbackend.business.api;

import com.bar.behdavardatabase.entity.CatalogEntity;

public interface CatalogBusiness {
    CatalogEntity findById(Long id);
}
