package com.bar.behdavarcommon.enumeration;

public enum ResultType implements AbstractEnum<Integer> {
    REQUEST_TIME(0),
    NO_ANSWER(1),
    FULL_SETTLEMENT(2),
    LEGAL_REFERENCE(3);

    private Integer value;

    ResultType(Integer s) {
        this.value = s;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
