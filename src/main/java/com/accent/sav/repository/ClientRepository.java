package com.accent.sav.repository;

import com.accent.sav.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "(select * from client c)", nativeQuery = true)
    public List<Client> findAllClients();

    @Query(value = "(select * from client c where c.id = :idClient)", nativeQuery = true)
    public Client findByIdClient(int idClient);
}
