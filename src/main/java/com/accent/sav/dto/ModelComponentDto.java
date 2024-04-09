package com.accent.sav.dto;


public class ModelComponentDto {

    private int id;

    private CardModelDto cardModel;

    private ComponentDto component;

    private Double quantity;

    public ModelComponentDto() {
        super();
    }

    public ModelComponentDto(int id, CardModelDto cardModel, ComponentDto component, Double quantity) {
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

    public CardModelDto getCardModel() {
        return cardModel;
    }

    public void setCardModel(CardModelDto cardModel) {
        this.cardModel = cardModel;
    }

    public ComponentDto getComponent() {
        return component;
    }

    public void setComponent(ComponentDto component) {
        this.component = component;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
