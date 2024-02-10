package com.accent.mahdia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_compnent")
public class CategoryComponent {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "measurement_unit", length = 10)
    private String measurementUnit;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Component> components;

    public CategoryComponent() {
        super();
    }

    public CategoryComponent(int id, String category, String measurementUnit, List<Component> components) {
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

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
