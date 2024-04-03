package com.accent.mahdia.dto;

public class CardSavDto {
    private ComponentDto componentDto;
    private Integer quantity;

    public CardSavDto(ComponentDto componentDto, Integer quantity) {
        this.componentDto = componentDto;
        this.quantity = quantity;
    }

    public ComponentDto getComponentDto() {
        return componentDto;
    }

    public void setComponentDto(ComponentDto componentDto) {
        this.componentDto = componentDto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
