package com.accent.mahdia.dto;

import com.accent.mahdia.entities.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class CategoryComponentDto {
    private int id;

    private String category;

    private String measurementUnit;

    private List<ComponentDto> components;

    public CategoryComponentDto() {
        super();
    }

    public CategoryComponentDto(int id, String category, String measurementUnit, List<ComponentDto> components) {
        this.id = id;
        this.category = category;
        this.measurementUnit = measurementUnit;
        this.components = components;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public List<ComponentDto> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentDto> components) {
        this.components = components;
    }
}
