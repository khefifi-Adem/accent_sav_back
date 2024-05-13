package com.accent.sav.entities.types;

public enum RapportFormat {

    WORD("word"), PDF("pdf"), EXCEL("excel"), XLS("xls");

    private String value;

    private RapportFormat(String state) {
        this.value = state;
    }


    public static RapportFormat fromValue(String x) {
        if (x == null) {
            return null;
        }
        switch (x) {
            case "word":
                return WORD;
            case "pdf":
                return PDF;
            case "excel":
                return EXCEL;
            case "xls":
                return XLS;
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
