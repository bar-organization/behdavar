package com.bar.behdavarcommon.enumeration;

public enum PaymentType implements AbstractEnum<Integer> {
    CHECK(0),
    CASH(1),
    NOTEBOOK(2),
    OTHER(3),
    INSTALLMENT_LOCATION_CASH(4),
    INSTALLMENTS(5);

    private Integer value;

    PaymentType(Integer s) {
        this.value = s;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
