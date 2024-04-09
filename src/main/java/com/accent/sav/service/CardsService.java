package com.accent.sav.service;

import com.accent.sav.dto.CardStockCountDto;
import com.accent.sav.dto.CardsAddDto;
import com.accent.sav.dto.CardsDto;
import com.accent.sav.entities.Cards;

import java.util.List;

public interface CardsService {
    public List<Cards> findAll();
    public List<Cards> getCardsByIdClient(Integer idClient);

    public List<Cards> add(CardsAddDto cardsDto);

    public List<CardsDto> update(List<CardsDto> cardsDto);

    public Boolean delete (int id);

    CardsDto getById(Integer id);

    List<CardStockCountDto> getStockCount();
}
