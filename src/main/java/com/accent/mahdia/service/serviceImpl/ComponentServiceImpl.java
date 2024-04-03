package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.CardSavDto;
import com.accent.mahdia.dto.ComponentDto;
import com.accent.mahdia.entities.Component;
import com.accent.mahdia.entities.ComponentQuantity;
import com.accent.mahdia.entities.HistoriqueMaintenance;
import com.accent.mahdia.repository.ComponentRepository;
import com.accent.mahdia.security.exception.ResourceAlreadyExistException;
import com.accent.mahdia.security.exception.ResourceNotFoundException;
import com.accent.mahdia.service.ComponentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(Component.class);

    @Override
    public List<Component> findAllComponent() {
        logger.info("===============================================");
        logger.info("===============Get All Component===============");
        logger.info("===============================================");
        return componentRepository.findAllComponents();
    }

    @Override
    public List<Component> findComponentsByIdModel(Integer idModel) {
        logger.info("========================================================");
        logger.info("===============Get All Component By Model===============");
        logger.info("========================================================");
        return componentRepository.findComponentsByIdModel(idModel);
    }

    @Override
    public ComponentDto addComponent(ComponentDto componentDto) {
        try {
            if (this.componentRepository.existsById(componentDto.getId())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing id " + componentDto.getId());
            }

            Component component = this.mapper.map(componentDto, Component.class);
            Component componentAdded = this.componentRepository.save(component);
            logger.info("===============Component Added===============");
            return this.mapper.map(componentAdded, ComponentDto.class);
        } catch (ResourceAlreadyExistException e) {
            // Log the exception
            logger.error("Failed to add component with id: {}. Reason: {}", componentDto.getId(), e.getMessage());
            // Return 409 Conflict status code
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Component with id " + componentDto.getId() + " already exists", e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to add component. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add component", e);
        }
    }

    @Override
    public ComponentDto updateComponentDto(ComponentDto componentDto) {
        try {
            if (componentDto != null) {
                Component component = this.componentRepository.findById(componentDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Component with id " + componentDto.getId() + " not found"));

                Component componentUpdate = this.mapper.map(componentDto, Component.class);
                this.componentRepository.saveAndFlush(componentUpdate);
                logger.info("===============Component Updated id: {}===============", componentUpdate.getId());
                ComponentDto result = this.mapper.map(componentUpdate, ComponentDto.class);
                return result;
            } else {
                // Log and return 400 Bad Request status code if componentDto is null
                logger.error("ComponentDto is null");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ComponentDto is null");
            }
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to update component with id: {}. Reason: {}", componentDto.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to update component. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update component", e);
        }
    }

    @Override
    public Boolean updateComponentStock(CardSavDto[] cardSavDtos) {
        for (CardSavDto cardSavDto : cardSavDtos) {
            if (cardSavDto != null) {
                Component component = this.componentRepository.findById(cardSavDto.getComponentDto().getId())
                        .orElseThrow(() -> new RuntimeException("Component not found with ID: " + cardSavDto.getComponentDto().getId()));
                HistoriqueMaintenance historiqueMaintenance = new HistoriqueMaintenance();
                ComponentQuantity componentQuantity = new ComponentQuantity();
                int oldQuantity = component.getQuantity();
                int newQuantity = oldQuantity - cardSavDto.getQuantity();
                component.setQuantity(newQuantity);
                this.componentRepository.saveAndFlush(component);

                // Logging the updates
                System.out.println("Component ID: " + component.getId() +
                        ", Old Quantity: " + oldQuantity +
                        ", Quantity to be deducted: " + cardSavDto.getQuantity() +
                        ", New Quantity: " + newQuantity);
            }
        }
        return true;
    }


    @Override
    public Boolean deleteComponent(int id) {
        try {
            Component componentOld = this.componentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Component with id " + id + " does not exist"));

            this.componentRepository.deleteById(id);
            logger.info("===============Component Deleted id: {}===============", id);
            return true;
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to delete component with id: {}. Reason: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to delete component with id: {}. Reason: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete component", e);
        }
    }

    @Override
    public ComponentDto getComponentById(Integer idComponent) {
        try {
            Component component = this.componentRepository.findByIdComponent(idComponent);
            if (component == null) {
                logger.error("Component with ID {} does not exist.", idComponent);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Component with ID " + idComponent + " does not exist.");
            } else {
                logger.info("Retrieved component with ID {} successfully.", idComponent);
                return this.mapper.map(component, ComponentDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve component with ID {}: {}", idComponent, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve component", e);
        }
    }
}
