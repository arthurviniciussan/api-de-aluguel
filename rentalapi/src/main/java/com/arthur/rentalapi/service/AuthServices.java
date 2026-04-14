package com.arthur.rentalapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arthur.rentalapi.dto.request.RegisterClientRequest;
import com.arthur.rentalapi.entity.clients.Client;
import com.arthur.rentalapi.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServices implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterClientRequest request) {
        String encrypt = passwordEncoder.encode(request.password());
        Client newClient = new Client(request.name(), request.email(), encrypt, request.role());
        this.clientRepository.save(newClient);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));
    }
}