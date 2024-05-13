package com.accent.sav.dto;

import java.util.Date;

public class HistoiqueDto {
    private int id;

    private String model;

    private Date dateProd;

    private int card;

    public HistoiqueDto() {
        super();
    }

    public HistoiqueDto(int id, String model, Date dateProd, int card) {
        this.id = id;
        this.model = model;
        this.dateProd = dateProd;
        this.card = card;
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

    public Date getDateProd() {
        return dateProd;
    }

    public void setDateProd(Date dateProd) {
        this.dateProd = dateProd;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }
}