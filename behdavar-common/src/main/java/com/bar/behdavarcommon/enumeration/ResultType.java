package com.bar.behdavarcommon.enumeration;

public enum ResultType implements AbstractEnum<Integer> {
    RESULT_1(0),
    RESULT_2(1);

    private Integer value;

    ResultType(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
