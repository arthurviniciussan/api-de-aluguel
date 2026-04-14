CREATE TABLE client (
		id BIGINT PRIMARY KEY AUTO_INCREMENT,
		name VARCHAR(255) NOT NULL,
		email VARCHAR(255) NOT NULL UNIQUE,
		password VARCHAR(255) NOT NULL,
		role VARCHAR(255) NOT NULL
);

CREATE TABLE rental_type (
		id BIGINT PRIMARY KEY AUTO_INCREMENT,
		name VARCHAR(255) NOT NULL,
		description VARCHAR(255)
);

CREATE TABLE reservation (
		id BIGINT PRIMARY KEY AUTO_INCREMENT,
		client_id BIGINT NOT NULL,
		rental_type_id BIGINT NOT NULL,
		reservation_date DATE NOT NULL,
		FOREIGN KEY (client_id) REFERENCES client(id),
		FOREIGN KEY (rental_type_id) REFERENCES rental_type(id)
);