package com.accent.sav.dto;

public class ComponentBackupDto {

    private int id;

    private String reference;

    private Double value;

    private int backupRef;

    private CategoryComponentDto category;

    public ComponentBackupDto() {
        super();
    }

    public ComponentBackupDto(int id, String reference, Double value, int backupRef, CategoryComponentDto category) {
        this.id = id;
        this.reference = reference;
        this.value = value;
        this.backupRef = backupRef;
        this.category = category;
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

    public int getBackupRef() {
        return backupRef;
    }

    public void setBackupRef(int backupRef) {
        this.backupRef = backupRef;
    }

    public CategoryComponentDto getCategory() {
        return category;
    }

    public void setCategory(CategoryComponentDto category) {
        this.category = category;
    }
}
