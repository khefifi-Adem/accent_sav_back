package com.accent.mahdia.service;

import com.accent.mahdia.dto.ModelComponentDto;
import com.accent.mahdia.entities.ModelComponent;

import java.util.List;

public interface ModelComponentService {

    public List<ModelComponent> findAll();

    public ModelComponentDto add(ModelComponentDto modelComponentDto);

    public ModelComponentDto update(ModelComponentDto modelComponentDto);

    public Boolean delete (int id);

    ModelComponentDto getById(Integer id);

}
