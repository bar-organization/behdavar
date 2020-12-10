package com.bar.behdavarcommon.enumeration;

public enum ContractStatus implements AbstractEnum<Integer> {
    AVAILABLE(0),
    CLEARING(1),
    RAW(2),
    LEGAL(3),
    PARKING(4),
    RETURN(5);

    private Integer value;

    ContractStatus(Integer s) {
        this.value = s;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public static ContractStatus getByName(String value){
        for(ContractStatus v : values()){
            if( v.name().equals(value)){
                return v;
            }
        }
        return null;
    }

}
