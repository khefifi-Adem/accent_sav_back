package com.accent.mahdia.dto;

public class ComponentQuantityDto {

    private int id;

    private HistoriqueMaintenanceDto historiqueMaintenance;

    private ComponentDto component;

    private Integer quantity;

    public ComponentQuantityDto() {
        super();
    }

    public ComponentQuantityDto(int id, HistoriqueMaintenanceDto historiqueMaintenance, ComponentDto component, Integer quantity) {
        this.id = id;
        this.historiqueMaintenance = historiqueMaintenance;
        this.component = component;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HistoriqueMaintenanceDto getHistoriqueMaintenance() {
        return historiqueMaintenance;
    }

    public void setHistoriqueMaintenance(HistoriqueMaintenanceDto historiqueMaintenance) {
        this.historiqueMaintenance = historiqueMaintenance;
    }

    public ComponentDto getComponent() {
        return component;
    }

    public void setComponent(ComponentDto component) {
        this.component = component;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
