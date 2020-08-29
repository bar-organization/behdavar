package com.bar.behdavarcommon.enumeration;

public enum PaymentType implements AbstractEnum<Integer> {
    PAYMENT_TYPE_1(0),
    PAYMENT_TYPE_2(1);

    private Integer value;

    PaymentType(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
