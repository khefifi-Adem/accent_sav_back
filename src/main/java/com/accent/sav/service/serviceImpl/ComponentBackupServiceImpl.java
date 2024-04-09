package com.accent.sav.service.serviceImpl;

import com.accent.sav.dto.ComponentBackupDto;
import com.accent.sav.entities.ComponentBackup;
import com.accent.sav.repository.ComponentBackupRepository;
import com.accent.sav.security.exception.ResourceAlreadyExistException;
import com.accent.sav.security.exception.ResourceNotFoundException;
import com.accent.sav.service.ComponentBackupService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ComponentBackupServiceImpl implements ComponentBackupService {

    @Autowired
    ComponentBackupRepository componentBackupRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(ComponentBackup.class);
    @Override
    public List<ComponentBackup> findAllComponent() {
        logger.info("======================================================");
        logger.info("===============Get All Component Backup===============");
        logger.info("======================================================");
        return componentBackupRepository.findAllComponents();
    }

    @Override
    public ComponentBackupDto addComponent(ComponentBackupDto componentDto) {
        try {
            if (this.componentBackupRepository.existsById(componentDto.getId())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing id " + componentDto.getId());
            }

            ComponentBackup component = this.mapper.map(componentDto, ComponentBackup.class);
            ComponentBackup componentAdded = this.componentBackupRepository.save(component);
            logger.info("===============Component Backup Added===============");
            return this.mapper.map(componentAdded, ComponentBackupDto.class);
        } catch (ResourceAlreadyExistException e) {
            // Log the exception
            logger.error("Failed to add component backup with id: {}. Reason: {}", componentDto.getId(), e.getMessage());
            // Return 409 Conflict status code
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Component backup with id " + componentDto.getId() + " already exists", e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to add component backup. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add component", e);
        }    }

    @Override
    public ComponentBackupDto updateComponentDto(ComponentBackupDto componentDto) {
        try {
            if (componentDto != null) {
                ComponentBackup component = this.componentBackupRepository.findById(componentDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Component with id " + componentDto.getId() + " not found"));

                ComponentBackup componentUpdate = this.mapper.map(componentDto, ComponentBackup.class);
//                componentUpdate.setBackupRef(component.getBackupRef());
                this.componentBackupRepository.saveAndFlush(componentUpdate);
                logger.info("===============Component Backup Updated id: {}===============", componentUpdate.getId());
                ComponentBackupDto result = this.mapper.map(componentUpdate, ComponentBackupDto.class);
                return result;
            } else {
                // Log and return 400 Bad Request status code if componentDto is null
                logger.error("ComponentBackupDto is null");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ComponentDto is null");
            }
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to update component component with id: {}. Reason: {}", componentDto.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to update component backup. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update component", e);
        }
    }

    @Override
    public Boolean deleteComponent(int id) {
        try {
            ComponentBackup componentOld = this.componentBackupRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Component backup with id " + id + " does not exist"));

            this.componentBackupRepository.deleteById(id);
            logger.info("===============Component backup Deleted id: {}===============", id);
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
        }    }

    @Override
    public ComponentBackupDto getComponentById(Integer idComponent) {
        try {
            ComponentBackup component = this.componentBackupRepository.findByIdComponent(idComponent);
            if (component == null) {
                logger.error("Component backup with ID {} does not exist.", idComponent);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Component with ID " + idComponent + " does not exist.");
            } else {
                logger.info("Retrieved component backup with ID {} successfully.", idComponent);
                return this.mapper.map(component, ComponentBackupDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve component backup with ID {}: {}", idComponent, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve component", e);
        }
    }
}
