package com.accent.sav.controller;

import com.accent.sav.dto.ModelComponentDto;
import com.accent.sav.entities.ModelComponent;
import com.accent.sav.service.ModelComponentService;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ModelComponentDto getModelComponentById (@PathVariable("id") Integer id) {
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
    public boolean deleteModelComponents(@PathVariable("id") int id) {
        return modelComponentService.delete(id);
    }
}
