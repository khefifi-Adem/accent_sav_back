package com.accent.sav.repository;

import com.accent.sav.entities.ModelComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelComponentRepository extends JpaRepository<ModelComponent, Integer> {
    @Query(value = "(select * from model_component c where c.id = :id)", nativeQuery = true)
    public ModelComponent findByIdModelComponent(int id);
    @Query(value = "(select * from model_component c where c.model_id = :id)", nativeQuery = true)
    public List<ModelComponent> findByIdModel(int id);
}
