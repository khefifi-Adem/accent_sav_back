package com.accent.sav.repository;

import com.accent.sav.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductionRepository extends JpaRepository<Production, Integer> {

    @Query(value = "(SELECT COUNT(card.id) AS card, card_model.model, production.id, production.date_production FROM production LEFT JOIN card ON production.id = card.production_id INNER JOIN card_model ON card.model_id = card_model.id GROUP BY production.id, card_model.model)", nativeQuery = true)
    public List<Object[]> getProductionHistorique();

}
