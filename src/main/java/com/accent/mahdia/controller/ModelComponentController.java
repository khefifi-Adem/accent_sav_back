package com.accent.mahdia.controller;

import com.accent.mahdia.dto.ModelComponentDto;
import com.accent.mahdia.entities.ModelComponent;
import com.accent.mahdia.service.ModelComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model-component")
@CrossOrigin
public class ModelComponentController {
    
    @Autowired
    ModelComponentService modelComponentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<ModelComponent> findAllModelComponent() {
        return modelComponentService.findAll();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody ModelComponentDto getModelComponenttById (@PathVariable("id") Integer id) {
        return modelComponentService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ModelComponentDto addModelComponent (@RequestBody ModelComponentDto modelComponentDto) {
        return modelComponentService.add(modelComponentDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ModelComponentDto updateModelComponentDto (@RequestBody ModelComponentDto modelComponentDto) {
        return modelComponentService.update(modelComponentDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteCards(@PathVariable("id") int id) {
        return modelComponentService.delete(id);
    }
}
