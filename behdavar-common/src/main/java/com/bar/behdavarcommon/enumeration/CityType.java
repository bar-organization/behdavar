package com.bar.behdavarcommon.enumeration;

public enum CityType implements AbstractEnum<String> {
    CENTER("C"),
    NORTH("N"),
    SOUTH("S"),
    WEST("W"),
    EAST("E");

    private String value;

    CityType(String s) {
        this.value = getValue();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static class CityTypeTypeConverter extends AbstractEnumConverter<CityType, String> {
        public CityTypeTypeConverter(Class<CityType> clazz) {
            super(clazz);
        }
    }
}
