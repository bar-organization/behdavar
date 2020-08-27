package com.bar.behdavarcommon.enumeration;

public enum PursuitType implements AbstractEnum<Integer> {
    TYPE_1(0),
    TYPE_2(1);

    private Integer value;

    PursuitType(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
