package com.bar.behdavarcommon.enumeration;

public enum CityType implements AbstractEnum<String> {
    CENTER("C"),
    NORTH("N"),
    SOUTH("S"),
    WEST("W"),
    EAST("E");

    private String value;

    CityType(String s) {
        this.value = s;
    }

    @Override
    public String getValue() {
        return this.getValue();
    }

//    public static class CityTypeTypeConverter extends AbstractEnumConverter<CityType, String> {
//        public CityTypeTypeConverter(Class<CityType> clazz) {
//            super(clazz);
//        }
//    }
}
