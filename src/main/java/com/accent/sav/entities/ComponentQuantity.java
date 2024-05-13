package com.accent.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "component_quantity")
public class ComponentQuantity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "historique_maintenance_id")
    private HistoriqueMaintenance historiqueMaintenance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    @Column(name = "quantity")
    private Integer quantity;

    public ComponentQuantity() {
        super();
    }

    public ComponentQuantity(int id, HistoriqueMaintenance historiqueMaintenance, Component component, Integer quantity) {
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

    public HistoriqueMaintenance getHistoriqueMaintenance() {
        return historiqueMaintenance;
    }

    public void setHistoriqueMaintenance(HistoriqueMaintenance historiqueMaintenance) {
        this.historiqueMaintenance = historiqueMaintenance;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
