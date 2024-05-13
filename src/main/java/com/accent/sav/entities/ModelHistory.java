package com.accent.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "model_history")
public class ModelHistory {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction")
    private Boolean transaction;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "model_id")
    private CardModel cardModel;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "transaction_date")
    private Date transactionDate;

    public ModelHistory() {
        super();
    }

    public ModelHistory(Long id, Boolean transaction, CardModel cardModel, int quantity, Date transactionDate) {
        this.id = id;
        this.transaction = transaction;
        this.cardModel = cardModel;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTransaction() {
        return transaction;
    }

    public void setTransaction(Boolean transaction) {
        this.transaction = transaction;
    }

    public CardModel getCardModel() {
        return cardModel;
    }

    public void setCardModel(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
