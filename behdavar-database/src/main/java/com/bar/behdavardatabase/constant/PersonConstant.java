package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class PersonConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PERSON";
    public static final String ID = "PK_PERSON";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String FULL_NAME = "FULL_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String DESCRIPTION = "DESCRIPTION";

    private PersonConstant() {
        // only constant
    }
}
