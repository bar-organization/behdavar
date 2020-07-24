package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class CatalogConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CATALOG";
    public static final String SEQ_NAME =  "CATALOG" + BaseConstant.SEQUENCE;
    public static final String ID = "PK_CATALOG";
    public static final String ENGLISH_TITLE = "ENGLISH_TITLE";
    public static final String CODE = "CODE";
    public static final String ACTIVE = "ACTIVE";

    private CatalogConstant() {
        // only constant
    }
}
