package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.ModelComponentDto;
import com.accent.mahdia.entities.ModelComponent;
import com.accent.mahdia.repository.ModelComponentRepository;
import com.accent.mahdia.security.exception.ResourceAlreadyExistException;
import com.accent.mahdia.security.exception.ResourceNotFoundException;
import com.accent.mahdia.service.ModelComponentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ModelComponentServiceImpl implements ModelComponentService {

    @Autowired
    ModelComponentRepository modelComponentRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(ModelComponent.class);

    @Override
    public List<ModelComponent> findAll() {
        logger.info("===========================================");
        logger.info("===============Get All ModelComponent===============");
        logger.info("===========================================");
        return modelComponentRepository.findAll();
    }

    @Override
    public ModelComponentDto add(ModelComponentDto modelComponentDto){
        try {
        if (this.modelComponentRepository.existsById(modelComponentDto.getId())) {
            // throw exception
            throw new ResourceAlreadyExistException("Existing id " + modelComponentDto.getId());
        }
        ModelComponent modelComponent = this.mapper.map(modelComponentDto, ModelComponent.class);
        ModelComponent modelComponentAdded = this.modelComponentRepository.save(modelComponent);
        logger.info("===============ModelComponent Added===============");
        return this.mapper.map(modelComponentAdded, ModelComponentDto.class);
    } catch (ResourceAlreadyExistException e) {
        // Log the exception
        logger.error("Failed to add ModelComponent with id: {}. Reason: {}", modelComponentDto.getId(), e.getMessage());
        // Return 409 Conflict status code
        throw new ResponseStatusException(HttpStatus.CONFLICT, "ModelComponent with id " + modelComponentDto.getId() + " already exists", e);
    } catch (Exception e) {
        // Log any other unexpected exception
        logger.error("Failed to add ModelComponent. Reason: {}", e.getMessage());
        // Return 500 Internal Server Error status code
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add ModelComponent", e);
    }
    }

    @Override
    public ModelComponentDto update(ModelComponentDto modelComponentDto) {
        try {
            if (modelComponentDto != null) {
                ModelComponent modelComponent = this.modelComponentRepository.findById(modelComponentDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("modelComponentDto with id " + modelComponentDto.getId() + " not found"));

                ModelComponent modelComponentUpdate = this.mapper.map(modelComponentDto, ModelComponent.class);
                this.modelComponentRepository.saveAndFlush(modelComponentUpdate);
                logger.info("===============ModelComponent Updated id: {}===============", modelComponentUpdate.getId());
                ModelComponentDto result = this.mapper.map(modelComponentUpdate, ModelComponentDto.class);
                return result;
            } else {
                // Log and return 400 Bad Request status code if componentDto is null
                logger.error("ModelComponentDto is null");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ModelComponentDto is null");
            }
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to update ModelComponentDto with id: {}. Reason: {}", modelComponentDto.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to update ModelComponentDto. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update ModelComponentDto", e);
        }    }

    @Override
    public Boolean delete(int id) {
        try {
            ModelComponent modelComponentOld = this.modelComponentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("ModelComponent with id " + id + " does not exist"));

            this.modelComponentRepository.deleteById(id);
            logger.info("===============ModelComponent Deleted id: {}===============", id);
            return true;
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if card is not found
            logger.error("Failed to delete ModelComponent with id: {}. Reason: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to delete ModelComponent with id: {}. Reason: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete ModelComponent", e);
        }    }

    @Override
    public ModelComponentDto getById(Integer id) {
        try {
            ModelComponent modelComponent = this.modelComponentRepository.findByIdModelComponent(id);
            if (modelComponent == null) {
                logger.error("modelComponent with ID {} does not exist.", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "modelComponent with ID " + id + " does not exist.");
            } else {
                logger.info("Retrieved modelComponent with ID {} successfully.", id);
                return this.mapper.map(modelComponent, ModelComponentDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve modelComponent with ID {}: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve modelComponent", e);
        }
    }
}
