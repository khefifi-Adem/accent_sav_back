package com.accent.sav.controller;

import com.accent.sav.dto.HistoiqueDto;
import com.accent.sav.dto.ProductionDto;
import com.accent.sav.entities.Production;
import com.accent.sav.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/production")
@CrossOrigin
public class ProductionController {

    @Autowired
    ProductionService productionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<Production> findAllProductions() {
        return productionService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ProductionDto addProduction (@RequestBody ProductionDto productionDto) {
        return productionService.add(productionDto);
    }

    @RequestMapping(value = "/gethistorique", method = RequestMethod.GET)
    public @ResponseBody List<HistoiqueDto> getProductionHistorique () {
        return productionService.getProductionHistorique();
    }
}
