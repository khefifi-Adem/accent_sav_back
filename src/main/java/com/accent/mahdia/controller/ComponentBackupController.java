package com.accent.mahdia.controller;

import com.accent.mahdia.dto.ComponentBackupDto;
import com.accent.mahdia.entities.ComponentBackup;
import com.accent.mahdia.service.ComponentBackupService;
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
    public @ResponseBody ComponentBackupDto addComponent (@RequestBody ComponentBackupDto componentBackupDto) {
        return componentBackupService.addComponent(componentBackupDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ComponentBackupDto updateComponent (@RequestBody ComponentBackupDto componentBackupDto) {
        return componentBackupService.updateComponentDto(componentBackupDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteComponent(@PathVariable("id") int id) {
        return componentBackupService.deleteComponent(id);
    }
}
