package com.accent.mahdia.repository;

import com.accent.mahdia.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthoritiesRepository extends JpaRepository<Authority, Integer> {
    @Query(value = "(select * from  authority a where a.id=:idAuthority)", nativeQuery = true)
    public Authority findByIdAuthority(int idAuthority);
}
