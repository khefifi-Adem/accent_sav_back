package com.accent.mahdia.dto;


import javax.persistence.*;

public class ComponentDto {

    private int id;

    private String reference;

    private Double value;

    private CategoryComponentDto category;

    public ComponentDto() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CategoryComponentDto getCategory() {
        return category;
    }

    public void setCategory(CategoryComponentDto category) {
        this.category = category;
    }
}
