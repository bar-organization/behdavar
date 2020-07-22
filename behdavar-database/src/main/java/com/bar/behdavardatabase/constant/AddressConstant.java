package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class AddressConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "ADDRESS";
    public static final String ID = "PK_ADDRESS";
    public static final String ADDRESS_DETAIL = "ADDRESS_DETAIL";
    public static final String FK_CITY = "FK_CITY";
    public static final String CITY_FK_CONSTRAINT = "ADDRESS_CITY_FK";

    private AddressConstant() {
        // only constant
    }
}
