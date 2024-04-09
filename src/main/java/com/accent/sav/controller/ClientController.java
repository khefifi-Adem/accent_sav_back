package com.accent.sav.controller;

import com.accent.sav.dto.ClientDto;
import com.accent.sav.entities.Client;
import com.accent.sav.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<Client> findAllClients() {
        return clientService.findAllClients();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody ClientDto getClientById (@PathVariable("id") Integer id) {
        return clientService.getClientById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ClientDto addCategoryComponent (@RequestBody ClientDto clientDto) {
        return clientService.addClient(clientDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ClientDto updateClientDto (@RequestBody ClientDto clientDto) {
        return clientService.updateClientDto(clientDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteClient(@PathVariable("id") int id) {
        return clientService.deleteClient(id);
    }
}
