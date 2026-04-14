package com.arthur.rentalapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ClientRequest(
	@NotNull(message = "Name cannot be null") String name,

	@Email(message = "Email should be valid") String email, 

	@NotBlank(message = "Password cannot be blank")
    String password) {}