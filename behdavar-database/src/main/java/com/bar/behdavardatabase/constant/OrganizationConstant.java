package com.bar.behdavardatabase.constant;

import com.bar.behdavardatabase.constant.common.BaseConstant;

public final class OrganizationConstant extends BaseConstant {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "ORGANIZATION";
    public static final String ID = "PK_ORGANIZATION";
    public static final String TITLE = "TITLE";
    public static final String CONTACT_NUMBER = "CONTACT_NUMBER";
    public static final String FAX = "FAX";
    public static final String EMAIL = "EMAIL";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String FK_ADDRESS = "FK_ADDRESS";
    public static final String FK_PERSON = "FK_PERSON";
    public static final String ADDRESS_FK_CONSTRAINT = "ORGANIZATION_ADDRESS_FK";
    public static final String PERSON_FK_CONSTRAINT = "ORGANIZATION_PERSON_FK";

    private OrganizationConstant() {
        // only constant
    }
}
