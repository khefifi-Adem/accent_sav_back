package com.accent.sav.dto;

public class AuthorityInfoDto {
    private int id;

    private String name;

    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "AuthorityInfoDto [id=" + id + ", name=" + name + ", label=" + label + "]";
    }

}
