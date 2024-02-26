package com.accent.mahdia.entities;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class Cards {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imei", length = 50)
    private String imei;

    @Column(name = "num_serie", length = 50)
    private String numSerie;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CardModel cardModel;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Cards() {
        super();
    }

    public Cards(int id, String imei, String numSerie, CardModel cardModel, Client client) {
        this.id = id;
        this.imei = imei;
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public CardModel getCardModel() {
        return cardModel;
    }

    public void setCardModel(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
