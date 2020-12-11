package com.bar.behdavarcommon.enumeration;

public enum Period implements AbstractEnum<Integer> {
    MONTHLY(0),
    THREE_MONTH(1),
    SIX_MONTH(2),
    YEAR(3);

    private Integer value;

    Period(Integer s) {
        this.value = s;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
