package com.accent.sav.entities;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "buy_date")
    private Date buyDate;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CardModel cardModel;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "production_id")
    private Production production;

    public Cards() {
        super();
    }

    public Cards(int id, String imei, String numSerie, Date addDate, Date buyDate, CardModel cardModel, Client client, Production production) {
        this.id = id;
        this.imei = imei;
        this.numSerie = numSerie;
        this.addDate = addDate;
        this.buyDate = buyDate;
        this.cardModel = cardModel;
        this.client = client;
        this.production = production;
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

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
}
