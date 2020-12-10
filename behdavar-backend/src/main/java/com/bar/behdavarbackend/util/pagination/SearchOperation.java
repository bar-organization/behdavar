package com.bar.behdavarbackend.util.pagination;

import com.bar.behdavarcommon.enumeration.AbstractEnum;
import com.bar.behdavarcommon.enumeration.ContractStatus;

public enum SearchOperation implements AbstractEnum<String> {
    GREATER_THAN("ge"),
    LESS_THAN("le"),
    GREATER_THAN_EQUAL("gte"),
    LESS_THAN_EQUAL("lte"),
    NOT_EQUAL("neq"),
    EQUAL("eq"),
    MATCH("m"),
    MATCH_START("ms"),
    MATCH_END("me"),
    IN("in"),
    IS_NULL("isNull"),
    NOT_NULL("notNull"),
    NOT_IN("nin");

    private String value;

    SearchOperation(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}