package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class CityConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CITY";
    public static final String ID = "PK_CITY";
    public static final String FK_PROVINCE = "FK_PROVINCE";
    public static final String PROVINCE_FK_CONSTRAINT = "CITY_PROVINCE_FK";

    private CityConstant() {
        // only constant
    }
}
