package com.accent.sav.service;

import com.accent.sav.dto.ModelComponentDto;
import com.accent.sav.entities.ModelComponent;

import java.util.List;

public interface ModelComponentService {

    public List<ModelComponent> findAll();

    public ModelComponentDto add(ModelComponentDto modelComponentDto);

    public ModelComponentDto update(ModelComponentDto modelComponentDto);

    public Boolean delete (int id);

    ModelComponentDto getById(Integer id);
}
