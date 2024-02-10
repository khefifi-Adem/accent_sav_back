package com.accent.mahdia.service;

import com.accent.mahdia.dto.CategoryComponentDto;
import com.accent.mahdia.entities.CategoryComponent;
import com.accent.mahdia.entities.Component;

import java.util.List;

public interface CategoryComponentService {

    public List<CategoryComponent> findAllCategoryComponent();
    public CategoryComponentDto addCategoryComponent(CategoryComponentDto categoryComponentDto);

    public CategoryComponentDto updateCategoryComponentDto(CategoryComponentDto categoryComponentDto);

    public Boolean deleteCategoryComponent  (int idCategoryComponent);

    CategoryComponentDto getCategoryComponentById(Integer idCategoryComponent);
}
