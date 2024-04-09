package com.accent.sav.service.serviceImpl;

import com.accent.sav.dto.HistoiqueDto;
import com.accent.sav.dto.ProductionDto;
import com.accent.sav.entities.Production;
import com.accent.sav.repository.ProductionRepository;
import com.accent.sav.security.exception.ResourceAlreadyExistException;
import com.accent.sav.service.ProductionService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public List<HistoiqueDto> getProductionHistorique() {
        List<Object[]> historique = this.productionRepository.getProductionHistorique();
        List<HistoiqueDto> historiqueDtoList = new ArrayList<>();
        for (Object[] row : historique) {
            historiqueDtoList.add(new HistoiqueDto(
                    (Integer)row[2],
                    (String)row[1],
                    (Date)row[3],
                    ((BigInteger)row[0]).intValue()
            ));
        }
        return historiqueDtoList;
    }
}
