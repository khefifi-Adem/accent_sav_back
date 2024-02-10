package com.accent.mahdia.repository;

import com.accent.mahdia.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

    @Query(value = "(select * from component c where c.id = :idComponent)", nativeQuery = true)
    public Component findByIdComponent(int idComponent);

}
