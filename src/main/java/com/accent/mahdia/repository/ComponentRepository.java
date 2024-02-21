package com.accent.mahdia.repository;

import com.accent.mahdia.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

    @Query(value = "(select * from component c where c.id = :idComponent)", nativeQuery = true)
    public Component findByIdComponent(int idComponent);
    @Query(value = "(select * from component c)", nativeQuery = true)
    public List<Component> findAllComponents();

    @Query(value = "(insert into component (backup, reference, value, category_id, backup_ref) values (:backup, :reference, :value, :category, :Ref) )", nativeQuery = true)
    public Component addComponentRef(
            boolean backup,String reference, Number value,Integer category, Integer Ref
    );

}
