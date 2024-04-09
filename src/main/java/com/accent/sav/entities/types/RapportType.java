package com.accent.sav.entities.types;

public enum RapportType {

    PRODUCTION_LIST("production_list");

    private String value;

    private RapportType(String state) {
        this.value = state;
    }

    public static RapportType fromValue(String x) {
        if (x == null) {
            return null;
        }
        switch (x) {
            case "production_list":
                return PRODUCTION_LIST;

        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
