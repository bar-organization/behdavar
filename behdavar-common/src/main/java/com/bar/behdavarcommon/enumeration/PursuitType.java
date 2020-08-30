package com.bar.behdavarcommon.enumeration;

public enum PursuitType implements AbstractEnum<Integer> {
    PHONE_CALL(0),
    APPOINTMENT(1),
    NOTICE(2),
    PAYMENT(3),
    REFER_PROPERTY(4),
    OTHER(5),
    INQUIRY_BRANCH(6),
    RECEIPT_POST_FAX(7),
    SEND_SMS(8),
    REVIEW_DOCUMENT(9),
    REQUEST_DOCUMENT(10),
    PEACE_AND_RECONCILIATION(11),
    LETTER_HELP(12),
    LEGAL_REVIEW(13),
    REFER_LAWYER(14),
    PREPARING_REFER_LAWYER(15),
    REQUEST_RELIGION_DOCUMENTS(16),
    SEND_LETTER(17),
    EXECUTIVE_REGISTRATION_LETTER(18),
    NOTIFICATION_RECEIVE(19),
    REGISTRATION_EXECUTIVE_CLASS(20),
    PROPERTY_IDENTIFICATION(21),
    MISSION(22),
    REPRESENTATIVE_REFER(23),
    REQUEST_SEIZURE(24),
    IVR(25),
    UPDATED(26);

    private Integer value;

    PursuitType(Integer s) {
        this.value = getValue();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
