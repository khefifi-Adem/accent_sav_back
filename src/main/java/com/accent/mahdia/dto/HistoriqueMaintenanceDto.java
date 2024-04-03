package com.accent.mahdia.dto;

import java.util.Date;
import java.util.List;

public class HistoriqueMaintenanceDto {

    private int id;

    private CardsDto card;

    private List<ComponentQuantityDto> componentQuantities;

    private Date dateMaintenance;

    public HistoriqueMaintenanceDto() {
        super();
    }

    public HistoriqueMaintenanceDto(int id, CardsDto card, List<ComponentQuantityDto> componentQuantities, Date dateMaintenance) {
        this.id = id;
        this.card = card;
        this.componentQuantities = componentQuantities;
        this.dateMaintenance = dateMaintenance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardsDto getCard() {
        return card;
    }

    public void setCard(CardsDto card) {
        this.card = card;
    }

    public List<ComponentQuantityDto> getComponentQuantities() {
        return componentQuantities;
    }

    public void setComponentQuantities(List<ComponentQuantityDto> componentQuantities) {
        this.componentQuantities = componentQuantities;
    }

    public Date getDateMaintenance() {
        return dateMaintenance;
    }

    public void setDateMaintenance(Date dateMaintenance) {
        this.dateMaintenance = dateMaintenance;
    }
}
