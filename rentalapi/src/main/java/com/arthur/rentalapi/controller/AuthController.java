package com.arthur.rentalapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.rentalapi.config.TokenService;
import com.arthur.rentalapi.dto.request.ClientRequest;
import com.arthur.rentalapi.dto.request.RegisterClientRequest;
import com.arthur.rentalapi.dto.response.ClientTokenResponse;
import com.arthur.rentalapi.dto.response.RegisterClientResponse;
import com.arthur.rentalapi.entity.clients.Client;
import com.arthur.rentalapi.repository.ClientRepository;
import com.arthur.rentalapi.service.AuthServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authServices;
    private final AuthenticationManager authenticationManager;
    private final ClientRepository repository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ClientTokenResponse> login(@Valid @RequestBody ClientRequest request) {
        var userAndPass = new UsernamePasswordAuthenticationToken(request.name(), request.password());
        var auth = this.authenticationManager.authenticate(userAndPass);

        var token = tokenService.generateToken((Client) auth.getPrincipal());
        return ResponseEntity.ok(new ClientTokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterClientResponse> register(@Valid @RequestBody RegisterClientRequest request) {
        if (this.repository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        authServices.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterClientResponse(request.name(), request.email()));
    }
}