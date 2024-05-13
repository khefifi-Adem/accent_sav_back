package com.accent.sav.service;

import com.accent.sav.dto.CategoryComponentDto;
import com.accent.sav.entities.CategoryComponent;

import java.util.List;

public interface CategoryComponentService {

    public List<CategoryComponent> findAllCategoryComponent();
    public CategoryComponentDto addCategoryComponent(CategoryComponentDto categoryComponentDto);

    public CategoryComponentDto updateCategoryComponentDto(CategoryComponentDto categoryComponentDto);

    public Boolean deleteCategoryComponent  (int idCategoryComponent);

    CategoryComponentDto getCategoryComponentById(Integer idCategoryComponent);
}
