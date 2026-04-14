package com.arthur.rentalapi.dto.request;

import java.sql.Date;

public record ReservationRequest(Long clientId, Long rentalTypeId, Date reservationDate) {
}