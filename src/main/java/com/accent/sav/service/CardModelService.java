package com.accent.sav.service;

import com.accent.sav.dto.CardModelDto;
import com.accent.sav.entities.CardModel;

import java.util.List;

public interface CardModelService {

    public List<CardModel> findAll();

    public CardModelDto add(CardModelDto cardModelDto);

    public CardModelDto update(CardModelDto cardModelDto);


    public Boolean delete (int id);

    CardModelDto getById(Integer id);
}
