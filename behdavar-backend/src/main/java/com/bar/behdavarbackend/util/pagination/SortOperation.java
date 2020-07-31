package com.bar.behdavarbackend.util.pagination;

import com.bar.behdavarcommon.enumeration.AbstractEnum;

public enum SortOperation implements AbstractEnum<String> {
    ASC("asc"),
    DSC("dsc");

    private String value;

    SortOperation(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
