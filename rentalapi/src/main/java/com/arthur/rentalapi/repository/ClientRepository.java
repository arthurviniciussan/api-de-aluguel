package com.arthur.rentalapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthur.rentalapi.entity.clients.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContaining(String name);

    Optional<Client> findByName(String name);

    Optional<Client> findByEmail(String email);
}