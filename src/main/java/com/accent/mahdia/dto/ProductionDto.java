package com.accent.mahdia.dto;


import java.util.Date;
import java.util.List;

public class ProductionDto {

    private int id;

    private List<CardsDto> cards;

    private Date dateProduction;


    public ProductionDto() {
        super();
    }

    public ProductionDto(int id, List<CardsDto> cards, Date dateProduction) {
        this.id = id;
        this.cards = cards;
        this.dateProduction = dateProduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CardsDto> getCards() {
        return cards;
    }

    public void setCards(List<CardsDto> cards) {
        this.cards = cards;
    }

    public Date getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(Date dateProduction) {
        this.dateProduction = dateProduction;
    }
}
