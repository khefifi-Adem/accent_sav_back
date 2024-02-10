package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.CategoryComponentDto;
import com.accent.mahdia.dto.ComponentDto;
import com.accent.mahdia.entities.CategoryComponent;
import com.accent.mahdia.entities.Component;
import com.accent.mahdia.repository.CategoryComponentRepository;
import com.accent.mahdia.repository.ComponentRepository;
import com.accent.mahdia.security.exception.ResourceAlreadyExistException;
import com.accent.mahdia.security.exception.ResourceNotFoundException;
import com.accent.mahdia.service.CategoryComponentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryComponentServiceImpl implements CategoryComponentService {

    @Autowired
    CategoryComponentRepository categoryComponentRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(CategoryComponent.class);

    @Override
    public List<CategoryComponent> findAllCategoryComponent() {
        logger.info("========================================================");
        logger.info("===============Get All Category Component===============");
        logger.info("========================================================");
        return categoryComponentRepository.findAll();
    }

    @Override
    public CategoryComponentDto addCategoryComponent(CategoryComponentDto categoryComponentDto) {
        try {
            if (this.categoryComponentRepository.existsById(categoryComponentDto.getId())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing id " + categoryComponentDto.getId());
            }

            CategoryComponent categoryComponent = this.mapper.map(categoryComponentDto, CategoryComponent.class);
            CategoryComponent categoryComponentAdded = this.categoryComponentRepository.save(categoryComponent);
            logger.info("===============Category Component Added===============");
            return this.mapper.map(categoryComponentAdded, CategoryComponentDto.class);
        } catch (ResourceAlreadyExistException e) {
            // Log the exception
            logger.error("Failed to add category component with id: {}. Reason: {}", categoryComponentDto.getId(), e.getMessage());
            // Return 409 Conflict status code
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category component with id " + categoryComponentDto.getId() + " already exists", e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to add component. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add category component", e);
        }    }

    @Override
    public CategoryComponentDto updateCategoryComponentDto(CategoryComponentDto categoryComponentDto) {
        try {
            if (categoryComponentDto != null) {
                CategoryComponent categoryComponent = this.categoryComponentRepository.findById(categoryComponentDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category component with id " + categoryComponentDto.getId() + " not found"));

                CategoryComponent categoryComponentUpdate = this.mapper.map(categoryComponentDto, CategoryComponent.class);
                this.categoryComponentRepository.saveAndFlush(categoryComponentUpdate);
                logger.info("===============Category Component Updated id: {}===============", categoryComponentUpdate.getId());
                CategoryComponentDto result = this.mapper.map(categoryComponentUpdate, CategoryComponentDto.class);
                return result;
            } else {
                // Log and return 400 Bad Request status code if componentDto is null
                logger.error("CategoryComponentDto is null");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CategoryComponentDto is null");
            }
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to update component with id: {}. Reason: {}", categoryComponentDto.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to update category component. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update category component", e);
        }    }

    @Override
    public Boolean deleteCategoryComponent(int idCategoryComponent) {
        try {
            CategoryComponent categoryComponentOld = this.categoryComponentRepository.findById(idCategoryComponent)
                    .orElseThrow(() -> new ResourceNotFoundException("Category Component with id " + idCategoryComponent + " does not exist"));

            this.categoryComponentRepository.deleteById(idCategoryComponent);
            logger.info("===============Component Deleted id: {}===============", idCategoryComponent);
            return true;
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if component is not found
            logger.error("Failed to delete category component with id: {}. Reason: {}", idCategoryComponent, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to delete category component with id: {}. Reason: {}", idCategoryComponent, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete category component", e);
        }    }

    @Override
    public CategoryComponentDto getCategoryComponentById(Integer idCategoryComponent) {
        try {
            CategoryComponent categoryComponent = this.categoryComponentRepository.findByIdComponent(idCategoryComponent);
            if (categoryComponent == null) {
                logger.error("Category component with ID {} does not exist.", idCategoryComponent);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Component with ID " + idCategoryComponent + " does not exist.");
            } else {
                logger.info("Retrieved category component with ID {} successfully.", idCategoryComponent);
                return this.mapper.map(categoryComponent, CategoryComponentDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve category component with ID {}: {}", idCategoryComponent, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve component", e);
        }    }
}
