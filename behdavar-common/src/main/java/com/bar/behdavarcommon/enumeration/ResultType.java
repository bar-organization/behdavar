package com.bar.behdavarcommon.enumeration;

public enum ResultType implements AbstractEnum<Integer> {
    RETURNED(0),
    NOT_ANNOUNCED(1),
    COLLECTION(2),
    RETURN(3),
    GUARANTEE(4),
    CONVERSION(5),
    DURATION(6),
    ACCEPTED(7),
    DAY_CHECK(8),
    SEND_TO_BANK(9),
    SEND_TO_FINANCE(10),
    SEND_TO_BRANCH(11),
    SEND_TO_AREA(12),
    NON_APPROVAL_AREA(13),
    FINAL_CONFIRMATION(14),
    RENEWAL_BANK_RESOURCES(15);

    private Integer value;

    ResultType(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
