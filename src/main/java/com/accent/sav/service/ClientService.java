package com.accent.sav.service;

import com.accent.sav.dto.ClientDto;
import com.accent.sav.entities.Client;

import java.util.List;

public interface ClientService {
    public List<Client> findAllClients();
    public ClientDto addClient(ClientDto clientDto);

    public ClientDto updateClientDto(ClientDto clientDto);

    public Boolean deleteClient(int id);

    ClientDto getClientById(Integer idClient);
}
