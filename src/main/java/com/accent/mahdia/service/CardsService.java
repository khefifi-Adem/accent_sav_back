package com.accent.mahdia.service;

import com.accent.mahdia.dto.CardsDto;
import com.accent.mahdia.entities.Cards;

import java.util.List;

public interface CardsService {
    public List<Cards> findAll();

    public CardsDto add(CardsDto cardsDto);

    public CardsDto update(CardsDto cardsDto);

    public Boolean delete (int id);

    CardsDto getById(Integer id);
}
