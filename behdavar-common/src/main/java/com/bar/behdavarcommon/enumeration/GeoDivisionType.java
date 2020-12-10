package com.bar.behdavarcommon.enumeration;

public enum GeoDivisionType implements AbstractEnum<String> {
    PROVINCE("P"),
    CITY("C"),
    ;

    private String value;
    ;

    GeoDivisionType(String s) {
        this.value = s;
    }

    @Override
    public String getValue() {
        return this.value;
    }

//    public static class GeoDivisionTypeConverter extends AbstractEnumConverter<GeoDivisionType, String> {
//        public GeoDivisionTypeConverter(Class<GeoDivisionType> clazz) {
//            super(clazz);
//        }
//    }
}
