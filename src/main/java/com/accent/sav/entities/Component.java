package com.accent.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "component")
public class Component {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "REFERENCE", length = 50)
    private String reference;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany
    @JoinColumn(name = "backup_ref")
    private List<ComponentBackup> backupRef;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryComponent category;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "component_id")
    private List<ModelComponent> modelComponents;

    public Component() {
        super();
    }

    public Component(int id, String reference, Double value, Integer quantity, List<ComponentBackup> backupRef, CategoryComponent category, List<ModelComponent> modelComponents) {
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

    public List<ComponentBackup> getBackupRef() {
        return backupRef;
    }

    public void setBackupRef(List<ComponentBackup> backupRef) {
        this.backupRef = backupRef;
    }

    public CategoryComponent getCategory() {
        return category;
    }

    public void setCategory(CategoryComponent category) {
        this.category = category;
    }

    public List<ModelComponent> getModelComponents() {
        return modelComponents;
    }

    public void setModelComponents(List<ModelComponent> modelComponents) {
        this.modelComponents = modelComponents;
    }
}
