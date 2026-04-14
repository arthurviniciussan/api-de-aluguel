package com.arthur.rentalapi.entity.clients;

public enum ClientRole {

	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

		private final String role;

	ClientRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
}