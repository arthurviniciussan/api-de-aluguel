package com.arthur.rentalapi.dto.request;

import com.arthur.rentalapi.entity.clients.ClientRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterClientRequest(@NotNull(message = "Name cannot be null") String name,

	@Email(message = "Email should be valid") String email, 

	@NotBlank(message = "Password cannot be blank")
  @Size(min = 8, max = 25, message = "Password must be between 8 and 25 characters")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    String password,
   ClientRole role) {
} 