package com.accent.mahdia.service;

import com.accent.mahdia.dto.CardsAddDto;
import com.accent.mahdia.dto.HistoiqueDto;
import com.accent.mahdia.dto.ProductionDto;
import com.accent.mahdia.entities.Cards;
import com.accent.mahdia.entities.Production;

import java.util.List;

public interface ProductionService {

    public List<Production> findAll();

    public ProductionDto add(ProductionDto productionDto);
    public List<HistoiqueDto> getProductionHistorique();

}
