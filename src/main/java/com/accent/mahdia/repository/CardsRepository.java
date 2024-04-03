package com.accent.mahdia.repository;

import com.accent.mahdia.entities.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardsRepository extends JpaRepository <Cards, Integer> {
    @Query(value = "(select * from card c where c.client_id = :idClient)", nativeQuery = true)
    public List<Cards> getCardsByIdClient(int idClient);

    @Query(value = "(select * from card c where c.id = :idCard)", nativeQuery = true)
    public Cards findByIdCard(int idCard);

    @Query(value = "(SELECT cm.model, COUNT(c.id) FROM sav.card c INNER JOIN sav.card_model cm ON c.model_id = cm.id where c.buy_date is null GROUP BY c.model_id, cm.model)", nativeQuery = true)
    public List<Object[]> getStockCount();
}
