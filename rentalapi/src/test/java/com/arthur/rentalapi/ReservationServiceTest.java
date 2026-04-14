package com.arthur.rentalapi;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.arthur.rentalapi.dto.request.ReservationRequest;
import com.arthur.rentalapi.entity.RentalType;
import com.arthur.rentalapi.entity.Reservation;
import com.arthur.rentalapi.entity.clients.Client;
import com.arthur.rentalapi.repository.ClientRepository;
import com.arthur.rentalapi.repository.RentalTypeRepository;
import com.arthur.rentalapi.repository.ReservationRepository;
import com.arthur.rentalapi.service.ReservationService;

import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@RequiredArgsConstructor
class ReservationServiceTest {

	@Mock
	private final ClientRepository clientRepository;

	@Mock
	private final RentalTypeRepository rentalTypeRepository;

	@Mock
	private final ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationService reservationService;


	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Test sucess on createReservation method in ReservationService")
	void testCreateReservationCase1() {
		Date reservationDate = Date.valueOf(LocalDate.now().plusDays(1)); // Data de reserva para amanhã
		ReservationRequest request = new ReservationRequest(1L, 1L, reservationDate);


		Client client = clientRepository.findById(request.clientId())

		.orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + request.clientId()));
		RentalType rentalType = rentalTypeRepository.findById(request.rentalTypeId())
		.orElseThrow(() -> new IllegalArgumentException("Rental type not found with id: " + request.rentalTypeId()));

		Reservation reservation = new Reservation();
		reservation.setClient(client);
		reservation.setRentalType(rentalType);
		reservation.setReservationDate(request.reservationDate());

		when(reservationRepository.save(reservation)).thenReturn(reservation);
		verify(reservationRepository, times(1)).save(reservation);
		reservationService.createReservation(request);
	}




}
