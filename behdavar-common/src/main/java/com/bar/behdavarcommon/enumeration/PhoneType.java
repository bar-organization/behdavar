package com.bar.behdavarcommon.enumeration;

public enum PhoneType implements AbstractEnum<String> {
    PHONE("P"),
    MOBILE("M"),
    FAX("F");

    private String value;
    ;

    PhoneType(String s) {
        this.value = getValue();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static class PhoneTypeConverter extends AbstractEnumConverter<PhoneType, String> {
        public PhoneTypeConverter(Class<PhoneType> clazz) {
            super(clazz);
        }
    }
}
