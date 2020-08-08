package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class ContactConstant extends BaseConstant {
    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CONTACT";
    public static final String SEQ_NAME = "CONTACT" + BaseConstant.SEQUENCE;
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";

    private ContactConstant() {
        // only constant
    }
}
