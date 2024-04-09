package com.accent.sav.controller;

import com.accent.sav.dto.CardSavDto;
import com.accent.sav.dto.ComponentDto;
import com.accent.sav.entities.Component;
import com.accent.sav.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/component")
@CrossOrigin
public class ComponentController {
    @Autowired
    ComponentService componentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<Component> findAllComponents() {
        return componentService.findAllComponent();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody ComponentDto getComponentById (@PathVariable("id") Integer id) {
        return componentService.getComponentById(id);
    }
    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET)
    public @ResponseBody List<Component> getComponentByIdModel (@PathVariable("id") Integer id) {
        return componentService.findComponentsByIdModel(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ComponentDto addComponent (@RequestBody ComponentDto componentDto) {
        return componentService.addComponent(componentDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ComponentDto updateComponent (@RequestBody ComponentDto componentDto) {
        return componentService.updateComponentDto(componentDto);
    }

    @RequestMapping(value = "/update/quantity", method = RequestMethod.PUT)
    public @ResponseBody Boolean updateComponentQuantity (@RequestBody CardSavDto[] cardSavDto) {
        return componentService.updateComponentStock(cardSavDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteComponent(@PathVariable("id") int id) {
        return componentService.deleteComponent(id);
    }
}
