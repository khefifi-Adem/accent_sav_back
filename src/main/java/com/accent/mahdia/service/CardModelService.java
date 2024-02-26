package com.accent.mahdia.service;

import com.accent.mahdia.dto.CardModelDto;
import com.accent.mahdia.entities.CardModel;

import java.util.List;

public interface CardModelService {

    public List<CardModel> findAll();

    public CardModelDto add(CardModelDto cardModelDto);

    public CardModelDto update(CardModelDto cardModelDto);


    public Boolean delete (int id);

    CardModelDto getById(Integer id);
}
