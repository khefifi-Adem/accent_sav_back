package com.accent.sav.service;


import com.accent.sav.dto.HistoriqueMaintenanceDto;
import com.accent.sav.entities.HistoriqueMaintenance;

import java.util.List;

public interface HistoriqueMaintenanceService {

    public List<HistoriqueMaintenance> findAllHistoriqueMaintenance();

    public HistoriqueMaintenanceDto add(HistoriqueMaintenanceDto historiqueMaintenanceDto);

}
