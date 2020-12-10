package com.bar.behdavarcommon.enumeration;

public enum RelationType implements AbstractEnum<Integer> {
    GUARANTOR(0),
    S1(1),
    S2(2);

    private Integer value;

    RelationType(Integer s) {
        this.value = s;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

//    public static class CityTypeTypeConverter extends AbstractEnumConverter<RelationType, Integer> {
//        public CityTypeTypeConverter(Class<RelationType> clazz) {
//            super(clazz);
//        }
//    }
}
