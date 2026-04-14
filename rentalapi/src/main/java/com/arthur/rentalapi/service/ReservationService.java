package com.arthur.rentalapi.service;

import org.springframework.stereotype.Service;

import com.arthur.rentalapi.dto.request.ReservationRequest;
import com.arthur.rentalapi.dto.response.ReservationResponse;
import com.arthur.rentalapi.entity.RentalType;
import com.arthur.rentalapi.entity.Reservation;
import com.arthur.rentalapi.entity.clients.Client;
import com.arthur.rentalapi.repository.ClientRepository;
import com.arthur.rentalapi.repository.RentalTypeRepository;
import com.arthur.rentalapi.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ClientRepository clientRepository;
    private final RentalTypeRepository rentalTypeRepository;
    private final ReservationRepository reservationRepository;

    public ReservationResponse createReservation(ReservationRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + request.clientId()));

        RentalType rentalType = rentalTypeRepository.findById(request.rentalTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Rental type not found with id: " + request.rentalTypeId()));

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setRentalType(rentalType);
        reservation.setReservationDate(request.reservationDate());

        reservationRepository.save(reservation);

        return new ReservationResponse(
                reservation.getId(),
                client.getId(),
                rentalType.getId(),
                reservation.getReservationDate()
        );
    }

    public ReservationResponse getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));

        return new ReservationResponse(
                reservation.getId(),
                reservation.getClient().getId(),
                reservation.getRentalType().getId(),
                reservation.getReservationDate()
        );
    }
}