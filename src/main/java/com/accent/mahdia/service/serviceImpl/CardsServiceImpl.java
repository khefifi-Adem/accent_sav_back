package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.CardsDto;
import com.accent.mahdia.entities.Cards;
import com.accent.mahdia.entities.Client;
import com.accent.mahdia.repository.CardsRepository;
import com.accent.mahdia.security.exception.ResourceAlreadyExistException;
import com.accent.mahdia.security.exception.ResourceNotFoundException;
import com.accent.mahdia.service.CardsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CardsServiceImpl implements CardsService {

    @Autowired
    CardsRepository cardsRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(Cards.class);

    @Override
    public List<Cards> findAll() {
        logger.info("===========================================");
        logger.info("===============Get All Cards===============");
        logger.info("===========================================");
        return cardsRepository.findAll();
    }

    @Override
    public CardsDto add(CardsDto cardsDto) {
        try {
            if (this.cardsRepository.existsById(cardsDto.getId())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing id " + cardsDto.getId());
            }

            Cards cards = this.mapper.map(cardsDto, Cards.class);
            Cards cardsAdded = this.cardsRepository.save(cards);
            logger.info("===============Cards Added===============");
            return this.mapper.map(cardsAdded, CardsDto.class);
        } catch (ResourceAlreadyExistException e) {
            // Log the exception
            logger.error("Failed to add cards with id: {}. Reason: {}", cardsDto.getId(), e.getMessage());
            // Return 409 Conflict status code
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cards with id " + cardsDto.getId() + " already exists", e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to add cards. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add cards", e);
        }
    }

    @Override
    public CardsDto update(CardsDto cardsDto) {
        try {
            if (cardsDto != null) {
                Cards cards = this.cardsRepository.findById(cardsDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Card with id " + cardsDto.getId() + " not found"));

                Cards cardsUpdate = this.mapper.map(cardsDto, Cards.class);
                this.cardsRepository.saveAndFlush(cardsUpdate);
                logger.info("===============Card Updated id: {}===============", cardsUpdate.getId());
                CardsDto result = this.mapper.map(cardsUpdate, CardsDto.class);
                return result;
            } else {
                // Log and return 400 Bad Request status code if componentDto is null
                logger.error("CardDto is null");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CategoryComponentDto is null");
            }
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to update CardDto with id: {}. Reason: {}", cardsDto.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to update CardDto. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update CardDto", e);
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            Cards cardOld = this.cardsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Card with id " + id + " does not exist"));

            this.cardsRepository.deleteById(id);
            logger.info("===============Card Deleted id: {}===============", id);
            return true;
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if card is not found
            logger.error("Failed to delete card with id: {}. Reason: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to delete card with id: {}. Reason: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete card", e);
        }
    }

    @Override
    public CardsDto getById(Integer id) {
        try {
            Client client = this.cardsRepository.findByIdCard(id);
            if (client == null) {
                logger.error("Card with ID {} does not exist.", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card with ID " + id + " does not exist.");
            } else {
                logger.info("Retrieved card with ID {} successfully.", id);
                return this.mapper.map(client, CardsDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve Card with ID {}: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve card", e);
        }
    }
}
