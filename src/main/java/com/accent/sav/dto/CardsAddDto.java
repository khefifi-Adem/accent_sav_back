package com.accent.sav.dto;

import com.accent.sav.entities.CardModel;
import com.accent.sav.entities.Cards;

import java.util.List;

public class CardsAddDto {

    private List<Cards> cards;

    private CardModel cardModel;

    public CardsAddDto() {
        super();
    }

    public CardsAddDto(List<Cards> cards, CardModel cardModel) {
        this.cards = cards;
        this.cardModel = cardModel;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }

    public CardModel getCardModel() {
        return cardModel;
    }

    public void setCardModel(CardModel cardModel) {
        this.cardModel = cardModel;
    }
}
