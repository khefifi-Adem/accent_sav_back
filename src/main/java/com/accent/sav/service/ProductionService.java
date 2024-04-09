package com.accent.sav.service;

import com.accent.sav.dto.HistoiqueDto;
import com.accent.sav.dto.ProductionDto;
import com.accent.sav.entities.Production;

import java.util.List;

public interface ProductionService {

    public List<Production> findAll();

    public ProductionDto add(ProductionDto productionDto);
    public List<HistoiqueDto> getProductionHistorique();

}
