package com.accent.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "card_model")
public class CardModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model", length = 50)
    private String model;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "model_id")
    private List<ModelComponent> modelComponents;


    public CardModel() {
        super();
    }

    public CardModel(int id, String model, List<ModelComponent> modelComponents) {
        this.id = id;
        this.model = model;
        this.modelComponents = modelComponents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ModelComponent> getModelComponents() {
        return modelComponents;
    }

    public void setModelComponents(List<ModelComponent> modelComponents) {
        this.modelComponents = modelComponents;
    }
}
