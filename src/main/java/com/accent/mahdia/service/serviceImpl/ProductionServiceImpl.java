package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.ClientDto;
import com.accent.mahdia.dto.ProductionDto;
import com.accent.mahdia.entities.Client;
import com.accent.mahdia.entities.Production;
import com.accent.mahdia.repository.ProductionRepository;
import com.accent.mahdia.security.exception.ResourceAlreadyExistException;
import com.accent.mahdia.service.ProductionService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(Production.class);
    @Override
    public List<Production> findAll() {
        logger.info("=================================================");
        logger.info("===============Get All Production ===============");
        logger.info("=================================================");
        return this.productionRepository.findAll();
    }

    @Override
    public ProductionDto add(ProductionDto productionDto) {
        try {
            if (this.productionRepository.existsById(productionDto.getId())) {
                // throw exception
                throw new ResourceAlreadyExistException("Existing id " + productionDto.getId());
            }

            Production production = this.mapper.map(productionDto, Production.class);
            Production productionAdded = this.productionRepository.save(production);
            logger.info("===============Production Added===============");
            return this.mapper.map(productionAdded, ProductionDto.class);
        } catch (ResourceAlreadyExistException e) {
            // Log the exception
            logger.error("Failed to add production with id: {}. Reason: {}", productionDto.getId(), e.getMessage());
            // Return 409 Conflict status code
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Production with id " + productionDto.getId() + " already exists", e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to add production. Reason: {}", e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add production", e);
        }
    }
}
