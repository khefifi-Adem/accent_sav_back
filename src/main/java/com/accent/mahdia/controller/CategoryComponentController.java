package com.accent.mahdia.controller;

import com.accent.mahdia.dto.CategoryComponentDto;
import com.accent.mahdia.entities.CategoryComponent;
import com.accent.mahdia.service.CategoryComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category_component")
@CrossOrigin
public class CategoryComponentController {
    @Autowired
    CategoryComponentService categoryComponentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<CategoryComponent> findAllCategoryComponent() {
        return categoryComponentService.findAllCategoryComponent();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody CategoryComponentDto getCategoryComponentById (@PathVariable("id") Integer id) {
        return categoryComponentService.getCategoryComponentById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody CategoryComponentDto addCategoryComponent (@RequestBody CategoryComponentDto categoryComponentDto) {
        return categoryComponentService.addCategoryComponent(categoryComponentDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody CategoryComponentDto updateCategoryComponent (@RequestBody CategoryComponentDto categoryComponentDto) {
        return categoryComponentService.updateCategoryComponentDto(categoryComponentDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteCategoryComponent(@PathVariable("id") int id) {
        return categoryComponentService.deleteCategoryComponent(id);
    }
}
