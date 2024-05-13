package com.accent.sav.service.serviceImpl;

import com.accent.sav.dto.HistoriqueMaintenanceDto;
import com.accent.sav.entities.Component;
import com.accent.sav.entities.ComponentQuantity;
import com.accent.sav.entities.HistoriqueMaintenance;
import com.accent.sav.repository.ComponentQuantityRepository;
import com.accent.sav.repository.ComponentRepository;
import com.accent.sav.repository.HistoriqueMaintenanceRepository;
import com.accent.sav.service.HistoriqueMaintenanceService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoriqueMaintenanceServiceImpl implements HistoriqueMaintenanceService {

    @Autowired
    HistoriqueMaintenanceRepository historiqueMaintenanceRepository;

    @Autowired
    ComponentQuantityRepository componentQuantityRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(HistoriqueMaintenance.class);
    @Override
    public List<HistoriqueMaintenance> findAllHistoriqueMaintenance() {
        logger.info("===========================================================");
        logger.info("===============Get All HistoriqueMaintenance===============");
        logger.info("===========================================================");
        return this.historiqueMaintenanceRepository.findAll();
    }

    @Override
    public HistoriqueMaintenanceDto add(HistoriqueMaintenanceDto historiqueMaintenanceDto) {
        try {
            // Mapping the DTO to the entity
            HistoriqueMaintenance historiqueMaintenance = this.mapper.map(historiqueMaintenanceDto, HistoriqueMaintenance.class);
            historiqueMaintenance.setComponentQuantities(null);

            // Save the HistoriqueMaintenance entity to get its ID
            HistoriqueMaintenance historiqueMaintenanceAdded = this.historiqueMaintenanceRepository.save(historiqueMaintenance);

            // Update the HistoriqueMaintenanceDto with the generated ID
            historiqueMaintenanceDto.setId(historiqueMaintenanceAdded.getId());

            // Mapping and setting the HistoriqueMaintenance entity for each ComponentQuantityDto
            List<ComponentQuantity> componentQuantities = historiqueMaintenanceDto.getComponentQuantities().stream()
                    .map(componentQuantityDto -> {
                        ComponentQuantity componentQuantity = this.mapper.map(componentQuantityDto, ComponentQuantity.class);
                        Component component = componentRepository.getOne(componentQuantity.getComponent().getId());
                        component.setQuantity(component.getQuantity() - componentQuantity.getQuantity());
                        componentRepository.saveAndFlush(component);
                        componentQuantity.setHistoriqueMaintenance(historiqueMaintenanceAdded);
                        return componentQuantity;
                    })
                    .collect(Collectors.toList());

            // Save all ComponentQuantity entities
            this.componentQuantityRepository.saveAll(componentQuantities);

            logger.info("===============Historique Maintenance Added===============");
            return historiqueMaintenanceDto;
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to add historique maintenance. Reason: {}", e.getMessage());
            // Return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add historique maintenance", e);
        }
    }


}
