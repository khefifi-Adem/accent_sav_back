package com.accent.mahdia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "component_backup")
public class ComponentBackup {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "REFERENCE", length = 50)
    private String reference;

    @Column(name = "VALUE")
    private Double value;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "backup_ref")
    private Component backupRef;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryComponent category;


    public ComponentBackup() {
        super();
    }
    public ComponentBackup(int id, String reference, Double value, Component backupRef, CategoryComponent category) {
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

    public Component getBackupRef() {
        return backupRef;
    }

    public void setBackupRef(Component backupRef) {
        this.backupRef = backupRef;
    }

    public CategoryComponent getCategory() {
        return category;
    }

    public void setCategory(CategoryComponent category) {
        this.category = category;
    }
}
