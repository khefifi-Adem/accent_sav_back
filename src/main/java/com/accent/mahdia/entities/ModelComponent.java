package com.accent.mahdia.entities;

import javax.persistence.*;

@Entity
@Table(name = "model_component")
public class ModelComponent {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CardModel cardModel;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    private Double quantity;

    public ModelComponent() {
        super();
    }

    public ModelComponent(int id, CardModel cardModel, Component component, Double quantity) {
        this.id = id;
        this.cardModel = cardModel;
        this.component = component;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardModel getCardModel() {
        return cardModel;
    }

    public void setCardModel(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
