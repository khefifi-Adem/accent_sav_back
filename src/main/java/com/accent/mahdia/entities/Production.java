package com.accent.mahdia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "production")
public class Production {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL)
    private List<Cards> cards;

    @Column(name = "date_production")
    private Date dateProduction;

    public Production() {
        super();
    }

    public Production(int id, List<Cards> cards, Date dateProduction) {
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

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }

    public Date getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(Date dateProduction) {
        this.dateProduction = dateProduction;
    }
}
