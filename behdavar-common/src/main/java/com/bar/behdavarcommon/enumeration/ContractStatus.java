package com.bar.behdavarcommon.enumeration;

public enum ContractStatus implements AbstractEnum<Integer> {
    STATUS_1(0),
    STATUS_2(1);

    private Integer value;

    ContractStatus(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
