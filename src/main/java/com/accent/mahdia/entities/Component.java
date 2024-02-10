package com.accent.mahdia.entities;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryComponent category;

    public Component() {
        super();
    }

    public Component(int id, String reference, Double value, CategoryComponent category) {
        this.id = id;
        this.reference = reference;
        this.value = value;
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

    public CategoryComponent getCategory() {
        return category;
    }

    public void setCategory(CategoryComponent category) {
        this.category = category;
    }
}
