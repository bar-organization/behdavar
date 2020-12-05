package com.bar.behdavarcommon.enumeration;

public enum ContractColor implements AbstractEnum<String> {
    BLACK("BLACK"), RED("RED"), GREEN("GREEN"), BLUE("BLUE"), GRAY("GRAY"), PURPLE("PURPLE");
    private String value;

    ContractColor(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
