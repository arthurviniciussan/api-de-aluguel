package com.arthur.rentalapi.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthur.rentalapi.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByClientId(Long clientId);

    List<Reservation> findByRentalTypeId(Long rentalTypeId);

    List<Reservation> findByReservationDate(Date date);

}