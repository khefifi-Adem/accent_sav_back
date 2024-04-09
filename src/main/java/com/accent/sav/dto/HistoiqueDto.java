package com.accent.sav.dto;

import java.util.Date;

public class HistoiqueDto {

    private int id;

    private String model;

    private Date date;

    private Integer card;

    public HistoiqueDto() {
        super();
    }

    public HistoiqueDto(int id, String model, Date date, Integer card) {
        this.id = id;
        this.model = model;
        this.date = date;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
