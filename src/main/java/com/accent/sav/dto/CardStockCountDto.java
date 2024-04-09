package com.accent.sav.dto;

public class CardStockCountDto {
    private String model;
    private Integer stock;

    public CardStockCountDto() {
        super();
    }

    public CardStockCountDto(String model, Integer stock) {
        this.model = model;
        this.stock = stock;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
