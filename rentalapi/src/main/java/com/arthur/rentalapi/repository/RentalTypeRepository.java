package com.arthur.rentalapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthur.rentalapi.entity.RentalType;

@Repository
public interface RentalTypeRepository extends JpaRepository<RentalType, Long> {

    List<RentalType> findByNameContaining(String name);

}