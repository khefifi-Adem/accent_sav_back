package com.accent.mahdia.service;


import com.accent.mahdia.dto.HistoriqueMaintenanceDto;
import com.accent.mahdia.entities.HistoriqueMaintenance;

import java.util.List;

public interface HistoriqueMaintenanceService {

    public List<HistoriqueMaintenance> findAllHistoriqueMaintenance();

    public HistoriqueMaintenanceDto add(HistoriqueMaintenanceDto historiqueMaintenanceDto);


}
