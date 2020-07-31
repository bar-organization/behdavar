package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class CatalogDetailConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CATALOG_DETAIL";
    public static final String SEQ_NAME = "CATALOG_DETAIL" + BaseConstant.SEQUENCE;
    public static final String ID = "PK_CATALOG_DETAIL";
    public static final String ENGLISH_TITLE = "ENGLISH_TITLE";
    public static final String TITLE = "TITLE";
    public static final String ACTIVE = "ACTIVE";
    public static final String CATALOG = "FK_CATALOG";
    public static final String CODE = "CODE";
    public static final String CATALOG_FK_CONSTRAINT = CatalogDetailConstant.TABLE_NAME+ "_" + CatalogDetailConstant.CATALOG;

    private CatalogDetailConstant() {
        // only constant
    }
}
