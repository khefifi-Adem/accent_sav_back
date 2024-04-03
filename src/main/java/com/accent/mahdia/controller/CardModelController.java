package com.accent.mahdia.controller;


import com.accent.mahdia.dto.CardModelDto;
import com.accent.mahdia.entities.CardModel;
import com.accent.mahdia.service.CardModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card-model")
@CrossOrigin
public class CardModelController {

    @Autowired
    CardModelService cardModelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<CardModel> findAllCardsModel() {
        return cardModelService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody CardModelDto getCardModeltById (@PathVariable("id") Integer id) {
        return cardModelService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody CardModelDto addCardModel (@RequestBody CardModelDto cardModelDto) {
        return cardModelService.add(cardModelDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody CardModelDto updateCardModelDto (@RequestBody CardModelDto cardModelDto) {
        return cardModelService.update(cardModelDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteCards(@PathVariable("id") int id) {
        return cardModelService.delete(id);
    }
}
