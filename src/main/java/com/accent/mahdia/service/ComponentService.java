package com.accent.mahdia.service;

import com.accent.mahdia.dto.ComponentDto;
import com.accent.mahdia.entities.Component;

import java.util.List;

public interface ComponentService {

    public List<Component> findAllComponent();
    public ComponentDto addComponent(ComponentDto componentDto);

    public ComponentDto updateComponentDto(ComponentDto componentDto);

    public Boolean deleteComponent(int id);

    ComponentDto getComponentById(Integer idComponent);
}
