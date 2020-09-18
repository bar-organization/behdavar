package com.bar.behdavarcommon.enumeration;

public enum ContractWeight implements AbstractEnum<Integer> {
    LEVEL1(1),
    LEVEL2(2),
    LEVEL3(3),
    LEVEL4(4);

    private Integer value;

    ContractWeight(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
