package com.arthur.rentalapi.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.arthur.rentalapi.entity.clients.Client;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
  private String secret;

	public String generateToken(Client client) {
		try {
			Algorithm algorithm =  Algorithm.HMAC256(secret);
			return JWT.create()
						.withIssuer("rental-api")
						.withSubject(client.getUsername())
						.withClaim("role", client.getRole().name())
						.withExpiresAt(generateExpirationDate())
						.sign(algorithm);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar token");
		}
	}

		public String validateToken(String token) {
			try { 
				Algorithm algorithm = Algorithm.HMAC256(secret);
				return JWT.require(algorithm)
									.withIssuer("rental-api")
									.build()
									.verify(token)
									.getSubject();
			} catch (JWTVerificationException e) {
				return "";
			}
		}

		private Instant generateExpirationDate() {
			return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
		}

	}
	
