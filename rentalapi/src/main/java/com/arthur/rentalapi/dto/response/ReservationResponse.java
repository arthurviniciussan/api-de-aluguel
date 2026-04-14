package com.arthur.rentalapi.dto.response;

import java.sql.Date;

public record ReservationResponse(Long id, Long clientId, Long rentalTypeId, Date reservationDate) {
}