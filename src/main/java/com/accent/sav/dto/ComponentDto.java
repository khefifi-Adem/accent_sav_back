package com.accent.sav.dto;


import java.util.List;

public class ComponentDto {

    private int id;

    private String reference;

    private Double value;

    private Integer quantity;

    private List<ComponentBackupDto> backupRef;

    private CategoryComponentDto category;

    private List<ModelComponentDto> modelComponents;

    public ComponentDto() {
        super();
    }

    public ComponentDto(int id, String reference, Double value, Integer quantity, List<ComponentBackupDto> backupRef, CategoryComponentDto category, List<ModelComponentDto> modelComponents) {
        this.id = id;
        this.reference = reference;
        this.value = value;
        this.quantity = quantity;
        this.backupRef = backupRef;
        this.category = category;
        this.modelComponents = modelComponents;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<ComponentBackupDto> getBackupRef() {
        return backupRef;
    }

    public void setBackupRef(List<ComponentBackupDto> backupRef) {
        this.backupRef = backupRef;
    }

    public CategoryComponentDto getCategory() {
        return category;
    }

    public void setCategory(CategoryComponentDto category) {
        this.category = category;
    }

    public List<ModelComponentDto> getModelComponents() {
        return modelComponents;
    }

    public void setModelComponents(List<ModelComponentDto> modelComponents) {
        this.modelComponents = modelComponents;
    }
}
