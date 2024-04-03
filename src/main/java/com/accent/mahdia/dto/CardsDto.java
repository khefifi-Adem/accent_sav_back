package com.accent.mahdia.dto;

import java.util.Date;

public class CardsDto {

    private int id;

    private String IMEI;

    private String numSerie;

    private CardModelDto cardModel;

    private ClientDto client;

    private Date addDate;

    private Date buyDate;

    public CardsDto() {
        super();
    }

    public CardsDto(int id, String IMEI, String numSerie, CardModelDto cardModel, ClientDto client, Date addDate, Date buyDate) {
        this.id = id;
        this.IMEI = IMEI;
        this.numSerie = numSerie;
        this.cardModel = cardModel;
        this.client = client;
        this.addDate = addDate;
        this.buyDate = buyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public CardModelDto getCardModel() {
        return cardModel;
    }

    public void setCardModel(CardModelDto cardModel) {
        this.cardModel = cardModel;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}
