package com.accent.mahdia.service;

import com.accent.mahdia.dto.CardStockCountDto;
import com.accent.mahdia.dto.CardsAddDto;
import com.accent.mahdia.dto.CardsDto;
import com.accent.mahdia.entities.Cards;

import java.util.List;

public interface CardsService {
    public List<Cards> findAll();
    public List<Cards> getCardsByIdClient(Integer idClient);

    public List<Cards> add(CardsAddDto cardsDto);

    public List update(List<CardsDto> cardsDto);

    public Boolean delete (int id);

    CardsDto getById(Integer id);

    List<CardStockCountDto> getStockCount();
}
