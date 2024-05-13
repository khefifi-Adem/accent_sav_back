package com.accent.sav.repository;

import com.accent.sav.entities.CategoryComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryComponentRepository extends JpaRepository<CategoryComponent, Integer> {

    @Query(value = "(select * from category_compnent c where c.id = :idCategoryComponent)", nativeQuery = true)
    public CategoryComponent findByIdComponent(int idCategoryComponent);

}
