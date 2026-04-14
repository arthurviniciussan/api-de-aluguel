package com.arthur.rentalapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.rentalapi.dto.response.ClientResponse;
import com.arthur.rentalapi.entity.clients.Client;
import com.arthur.rentalapi.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientResponse(client.getId(), client.getName(), client.getEmail()))
                .toList();
    }

    public ClientResponse getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));

        return new ClientResponse(client.getId(), client.getName(), client.getEmail());
    }
}