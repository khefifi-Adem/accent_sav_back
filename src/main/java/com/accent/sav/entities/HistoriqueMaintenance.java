package com.accent.sav.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "historique_maintenance")
public class HistoriqueMaintenance {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Cards card;

    @OneToMany(mappedBy = "historiqueMaintenance", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "historique_maintenance_id")
    private List<ComponentQuantity> componentQuantities;

    @Column(name = "date_maintenance")
    private Date dateMaintenance;

    public HistoriqueMaintenance() {
        super();
    }

    public HistoriqueMaintenance(int id, Cards card, List<ComponentQuantity> componentQuantities, Date dateMaintenance) {
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

    public Cards getCard() {
        return card;
    }

    public void setCard(Cards card) {
        this.card = card;
    }

    public List<ComponentQuantity> getComponentQuantities() {
        return componentQuantities;
    }

    public void setComponentQuantities(List<ComponentQuantity> componentQuantities) {
        this.componentQuantities = componentQuantities;
    }

    public Date getDateMaintenance() {
        return dateMaintenance;
    }

    public void setDateMaintenance(Date dateMaintenance) {
        this.dateMaintenance = dateMaintenance;
    }
}
