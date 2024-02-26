package com.accent.mahdia.repository;

import com.accent.mahdia.entities.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardModelRepository extends JpaRepository<CardModel, Integer> {
    @Query(value = "(select * from card_model c where c.id = :idCardModel)", nativeQuery = true)
    public CardModel findByIdCardModel(int idCardModel);
}
