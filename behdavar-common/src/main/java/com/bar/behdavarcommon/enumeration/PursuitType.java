package com.bar.behdavarcommon.enumeration;

public enum PursuitType implements AbstractEnum<Integer> {
    PHONE_CALL(0),
    SEND_SMS(1),
    SEND_NOTICE(2);

    private Integer value;

    PursuitType(Integer s) {
        this.value = s;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
