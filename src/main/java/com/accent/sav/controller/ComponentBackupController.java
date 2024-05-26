package com.accent.sav.controller;

import com.accent.sav.dto.ComponentBackupDto;
import com.accent.sav.entities.ComponentBackup;
import com.accent.sav.service.ComponentBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/component-category")
@CrossOrigin
public class ComponentBackupController {
    @Autowired
    ComponentBackupService componentBackupService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<ComponentBackup> findAllComponents() {
        return componentBackupService.findAllComponent();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody ComponentBackupDto getComponentById (@PathVariable("id") Integer id) {
        return componentBackupService.getComponentById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ComponentBackup addComponent (@RequestBody ComponentBackupDto componentBackupDto) {
        return componentBackupService.addComponent(componentBackupDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ComponentBackup updateComponent (@RequestBody ComponentBackupDto componentBackupDto) {
        return componentBackupService.updateComponentDto(componentBackupDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteComponent(@PathVariable("id") int id) {
        return componentBackupService.deleteComponent(id);
    }
}
