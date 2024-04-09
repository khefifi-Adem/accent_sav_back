package com.accent.sav.service;

import com.accent.sav.dto.CardSavDto;
import com.accent.sav.dto.ComponentDto;
import com.accent.sav.entities.Component;

import java.util.List;

public interface ComponentService {

    public List<Component> findAllComponent();

    public List<Component> findComponentsByIdModel(Integer idModel);

    public ComponentDto addComponent(ComponentDto componentDto);

    public ComponentDto updateComponentDto(ComponentDto componentDto);

    public Boolean updateComponentStock(CardSavDto[] cardSavDtos);

    public Boolean deleteComponent(int id);

    ComponentDto getComponentById(Integer idComponent);
}
