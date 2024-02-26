package com.accent.mahdia.dto;

public class CardsDto {

    private int id;

    private String IMEI;

    private String numSerie;

    private CardModelDto cardModel;

    private ClientDto client;

    public CardsDto() {
        super();
    }

    public CardsDto(int id, String IMEI, String numSerie, CardModelDto cardModel, ClientDto client) {
        this.id = id;
        this.IMEI = IMEI;
        this.numSerie = numSerie;
        this.cardModel = cardModel;
        this.client = client;
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
}
