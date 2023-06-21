package com.acme.webserviceslinerepair.client.domain.service;

import com.acme.webserviceslinerepair.client.domain.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<Client> getAll();
    Client getById(Long clientId);
    Client getByUsername(String username);
    Page<Client> getAll(Pageable pageable);
    List<Client> getByNameAndLastName(String names, String lastNames);
    Client create(Client client);
    Client update(Long clientId, Client client);
    ResponseEntity<?> delete(Long clientId);
}
