package com.accent.mahdia.controller;

import com.accent.mahdia.dto.CardStockCountDto;
import com.accent.mahdia.dto.CardsAddDto;
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

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public @ResponseBody List<CardStockCountDto> getStockCount () {
        return cardsService.getStockCount();
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public @ResponseBody List<Cards> getCardsByIdClient (@PathVariable("id") Integer id) {
        return cardsService.getCardsByIdClient(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody CardsDto getCardtById (@PathVariable("id") Integer id) {
        return cardsService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody List   <Cards> addCards (@RequestBody CardsAddDto cardsAddDto) {
        return cardsService.add(cardsAddDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody List<CardsDto> updateCardDto (@RequestBody List<CardsDto> cardsDto) {
        return cardsService.update(cardsDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteCard(@PathVariable("id") int id) {
        return cardsService.delete(id);
    }

}
