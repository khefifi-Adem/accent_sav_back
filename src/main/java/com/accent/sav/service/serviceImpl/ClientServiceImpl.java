package com.accent.sav.service.serviceImpl;

import com.accent.sav.dto.ClientDto;
import com.accent.sav.entities.Client;
import com.accent.sav.repository.ClientRepository;
import com.accent.sav.security.exception.ResourceAlreadyExistException;
import com.accent.sav.security.exception.ResourceNotFoundException;
import com.accent.sav.service.ClientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    
    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    protected ModelMapper mapper;
    
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    
    @Override
    public List<Client> findAllClients() {
        logger.info("============================================");
        logger.info("===============Get All Client===============");
        logger.info("============================================");
        return clientRepository.findAllClients();    
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        try {
            if (this.clientRepository.existsById(clientDto.getId())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing id " + clientDto.getId());
            }

            Client client = this.mapper.map(clientDto, Client.class);
            Client clientAdded = this.clientRepository.save(client);
            logger.info("===============Client Added===============");
            return this.mapper.map(clientAdded, ClientDto.class);
        } catch (ResourceAlreadyExistException e) {
            // Log the exception
            logger.error("Failed to add client with id: {}. Reason: {}", clientDto.getId(), e.getMessage());
            // Return 409 Conflict status code
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client with id " + clientDto.getId() + " already exists", e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to add client. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add client", e);
        }
    }

    @Override
    public ClientDto updateClientDto(ClientDto clientDto) {
        try {
            if (clientDto != null) {
                Client client = this.clientRepository.findById(clientDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("CLient with id " + clientDto.getId() + " not found"));

                Client clientUpdate = this.mapper.map(client, Client.class);
                this.clientRepository.saveAndFlush(clientUpdate);
                logger.info("===============Client Updated id: {}===============", clientUpdate.getId());
                ClientDto result = this.mapper.map(clientUpdate, ClientDto.class);
                return result;
            } else {
                // Log and return 400 Bad Request status code if clientDto is null
                logger.error("Client is null");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ClientDto is null");
            }
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if client is not found
            logger.error("Failed to update client with id: {}. Reason: {}", clientDto.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to update client. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update client", e);
        }
    }

    @Override
    public Boolean deleteClient(int id) {
        try {
            Client clienttOld = this.clientRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("cliebt with id " + id + " does not exist"));

            this.clientRepository.deleteById(id);
            logger.info("===============Client Deleted id: {}===============", id);
            return true;
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to delete component with id: {}. Reason: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to delete component backup with id: {}. Reason: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete component", e);
        }
    }

    @Override
    public ClientDto getClientById(Integer idClient) {
        try {
            Client client = this.clientRepository.findByIdClient(idClient);
            if (client == null) {
                logger.error("Client with ID {} does not exist.", idClient);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with ID " + idClient + " does not exist.");
            } else {
                logger.info("Retrieved client with ID {} successfully.", idClient);
                return this.mapper.map(client, ClientDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve Client with ID {}: {}", idClient, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve client", e);
        }
    }
}

