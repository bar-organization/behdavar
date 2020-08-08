package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class CatalogConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CATALOG";
    public static final String SEQ_NAME = "CATALOG" + BaseConstant.SEQUENCE;
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";
    public static final String ENGLISH_TITLE = "ENGLISH_TITLE";
    public static final String CODE = "CODE";
    public static final String ACTIVE = "ACTIVE";

    public static final Long RECEIVE_TYPE = 100L;
    public static final Long ASSURANCE_TYPE = 101L;
    public static final Long PURSUIT_TYPE = 102L;
    public static final Long BANK_TYPE = 103L;
    public static final Long CORPORATION_TYPE = 104L;

    private CatalogConstant() {
        // only constant
    }
}
