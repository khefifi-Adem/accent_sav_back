package com.accent.mahdia.controller;

import com.accent.mahdia.dto.CardsDto;
import com.accent.mahdia.entities.Cards;
import com.accent.mahdia.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@CrossOrigin
public class CardController {

    @Autowired
    CardsService cardsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<Cards> findAllCards() {
        return cardsService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody CardsDto getCardtById (@PathVariable("id") Integer id) {
        return cardsService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody CardsDto addCards (@RequestBody CardsDto cardsDto) {
        return cardsService.add(cardsDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody CardsDto updateCardDto (@RequestBody CardsDto cardsDto) {
        return cardsService.update(cardsDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteCard(@PathVariable("id") int id) {
        return cardsService.delete(id);
    }

}
