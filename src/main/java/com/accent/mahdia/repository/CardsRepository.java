package com.accent.mahdia.repository;

import com.accent.mahdia.entities.Cards;
import com.accent.mahdia.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardsRepository extends JpaRepository <Cards, Integer> {
    @Query(value = "(select * from card c where c.id = :idCard)", nativeQuery = true)
    public Client findByIdCard(int idCard);
}
