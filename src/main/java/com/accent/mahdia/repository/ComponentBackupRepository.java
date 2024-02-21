package com.accent.mahdia.repository;

import com.accent.mahdia.entities.ComponentBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComponentBackupRepository extends JpaRepository<ComponentBackup, Integer> {
    @Query(value = "(select * from component_backup c)", nativeQuery = true)
    public List<ComponentBackup> findAllComponents();

    @Query(value = "(select * from component_backup c where c.id = :idComponent)", nativeQuery = true)
    public ComponentBackup findByIdComponent(int idComponent);
}
