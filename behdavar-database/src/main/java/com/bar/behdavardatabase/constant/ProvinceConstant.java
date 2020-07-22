package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class ProvinceConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PROVINCE";
    public static final String ID = "PK_PROVINCE";
    public static final String PROVINCE_MAPPED_BY = "province";

    private ProvinceConstant() {
        // only constant
    }
}
