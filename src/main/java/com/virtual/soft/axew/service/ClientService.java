package com.virtual.soft.axew.service;

import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.model.Client;
import com.virtual.soft.axew.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll () {
        return repository.findAll();
    }

    public Client findById (Long id) {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ResourceNotFoundException("Client not found."));
    }
}
