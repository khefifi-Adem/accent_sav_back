package com.accent.sav.controller;

import com.accent.sav.dto.HistoriqueMaintenanceDto;
import com.accent.sav.entities.HistoriqueMaintenance;
import com.accent.sav.service.HistoriqueMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historique-maintenance")
@CrossOrigin
public class HistoriqueMaintenanceController {

    @Autowired
    HistoriqueMaintenanceService historiqueMaintenanceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<HistoriqueMaintenance> findAllHistoriqueMaintenance() {
        return historiqueMaintenanceService.findAllHistoriqueMaintenance();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HistoriqueMaintenanceDto add (@RequestBody HistoriqueMaintenanceDto historiqueMaintenanceDto) {
        return historiqueMaintenanceService.add(historiqueMaintenanceDto);
    }

}
