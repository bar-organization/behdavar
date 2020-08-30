package com.bar.behdavarcommon.enumeration;

public enum ContractType implements AbstractEnum<Integer> {
    BANKS(0),
    CARS(1);

    private Integer value;

    ContractType(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
