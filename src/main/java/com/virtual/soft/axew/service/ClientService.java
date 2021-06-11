package com.virtual.soft.axew.service;

import com.virtual.soft.axew.dto.client.ClientSaveDto;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.model.Address;
import com.virtual.soft.axew.model.Client;
import com.virtual.soft.axew.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clients = repository.findAll(pageable);
        return clients.getContent();
    }

    public Client findById (Long id) {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ResourceNotFoundException("Client not found."));
    }

    @Transactional
    public Client save (Client client) {
        return repository.save(client);
    }

    public Client convertFromDto (ClientSaveDto c) {
        Address address = new Address(c.getStreet(), c.getNumber(), c.getDistrict(), c.getZipCode());
        return new Client(c.getName(), c.getCpf(), c.getEmail())
                .setPhone(c.getPhone())
                .setBirthDate(c.getBirthDate())
                .setPassword(c.getPassword())
                .setAddress(address);
    }
}
