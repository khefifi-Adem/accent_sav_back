package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.CardModelDto;
import com.accent.mahdia.entities.CardModel;
import com.accent.mahdia.repository.CardModelRepository;
import com.accent.mahdia.security.exception.ResourceAlreadyExistException;
import com.accent.mahdia.security.exception.ResourceNotFoundException;
import com.accent.mahdia.service.CardModelService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CardModelServiceImpl implements CardModelService {

    @Autowired
    CardModelRepository cardModelRepository;
    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(CardModel.class);

    @Override
    public List<CardModel> findAll() {
        logger.info("===============================================");
        logger.info("===============Get All CardModel===============");
        logger.info("===============================================");
        return cardModelRepository.findAll();
    }

    @Override
    public CardModelDto add(CardModelDto cardModelDto) {
        try {
            if (this.cardModelRepository.existsById(cardModelDto.getId())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing id " + cardModelDto.getId());
            }

            CardModel cardModel = this.mapper.map(cardModelDto, CardModel.class);
            CardModel cardModelAdded = this.cardModelRepository.save(cardModel);
            logger.info("===============CardModel Added===============");
            return this.mapper.map(cardModelAdded, CardModelDto.class);
        } catch (ResourceAlreadyExistException e) {
            // Log the exception
            logger.error("Failed to add cardModel with id: {}. Reason: {}", cardModelDto.getId(), e.getMessage());
            // Return 409 Conflict status code
            throw new ResponseStatusException(HttpStatus.CONFLICT, "cardModel with id " + cardModelDto.getId() + " already exists", e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to add cardModel. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add cardModel", e);
        }
    }

    @Override
    public CardModelDto update(CardModelDto cardModelDto) {
        try {
            if (cardModelDto != null) {
                CardModel cardModel = this.cardModelRepository.findById(cardModelDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("cardModel with id " + cardModelDto.getId() + " not found"));

                CardModel cardModelUpdate = this.mapper.map(cardModelDto, CardModel.class);
                this.cardModelRepository.saveAndFlush(cardModelUpdate);
                logger.info("===============cardModel Updated id: {}===============", cardModelUpdate.getId());
                CardModelDto result = this.mapper.map(cardModelUpdate, CardModelDto.class);
                return result;
            } else {
                // Log and return 400 Bad Request status code if clientDto is null
                logger.error("cardModelDto is null");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cardModelDto is null");
            }
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if client is not found
            logger.error("Failed to update cardModelDto with id: {}. Reason: {}", cardModelDto.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to update cardModel. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update cardModel", e);
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            CardModel cardModelOld = this.cardModelRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("cardModel with id " + id + " does not exist"));

            this.cardModelRepository.deleteById(id);
            logger.info("===============cardModel Deleted id: {}===============", id);
            return true;
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to delete cardModel with id: {}. Reason: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to delete cardModel with id: {}. Reason: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete cardModel", e);
        }
    }

    @Override
    public CardModelDto getById(Integer id) {
        try {
            CardModel cardModel = this.cardModelRepository.findByIdCardModel(id);
            if (cardModel == null) {
                logger.error("cardModel with ID {} does not exist.", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with ID " + id + " does not exist.");
            } else {
                logger.info("Retrieved cardModel with ID {} successfully.", id);
                return this.mapper.map(cardModel, CardModelDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve cardModel with ID {}: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve cardModel", e);
        }
    }
}
