package com.accent.mahdia.repository;

import com.accent.mahdia.entities.ModelComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModelComponentRepository extends JpaRepository<ModelComponent, Integer> {
    @Query(value = "(select * from model_component c where c.id = :id)", nativeQuery = true)
    public ModelComponent findByIdModelComponent(int id);
}
