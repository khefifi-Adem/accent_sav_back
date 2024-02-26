package com.accent.mahdia.dto;

import java.util.List;

public class CardModelDto {

    private int id;

    private String model;

    private List<ModelComponentDto> modelComponents;

    public CardModelDto() {
        super();
    }

    public CardModelDto(int id, String model, List<ModelComponentDto> modelComponents) {
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

    public List<ModelComponentDto> getModelComponents() {
        return modelComponents;
    }

    public void setModelComponents(List<ModelComponentDto> modelComponents) {
        this.modelComponents = modelComponents;
    }
}
