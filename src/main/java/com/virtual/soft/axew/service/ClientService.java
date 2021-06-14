package com.virtual.soft.axew.service;

import com.virtual.soft.axew.dto.client.ClientSaveDto;
import com.virtual.soft.axew.entity.Address;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.entity.enums.RoleEnum;
import com.virtual.soft.axew.exception.AuthorizationException;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.repository.ClientRepository;
import com.virtual.soft.axew.security.user.UserAuth;
import com.virtual.soft.axew.utils.UserAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Client> findAll (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clients = repository.findAll(pageable);
        return new PageImpl<>(clients.getContent(), pageable, clients.getTotalElements());
    }

    public Client findById (Long id) {
        UserAuth userAuth = UserAuthUtils.getAuthenticatedUser();
        if (userAuth == null || (!userAuth.hasRole(RoleEnum.ADMIN) && !userAuth.getId().equals(id))) {
            throw new AuthorizationException("Access Denied!");
        }

        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ResourceNotFoundException("Client not found."));
    }

    public Client findByEmail (String email) {
        return repository.findByEmail(email);
    }

    @Transactional
    public Client save (Client client) {
        addressService.save(client.getAddress());
        return repository.save(client);
    }

    public Client convertFromDto (ClientSaveDto c) {
        Client client = new Client(c.getName(), c.getCpf(), c.getEmail())
                .setPhone(c.getPhone())
                .setBirthDate(c.getBirthDate())
                .setPassword(passwordEncoder.encode(c.getPassword()));
        Address address = new Address(c.getStreet(), c.getNumber(),
                c.getDistrict(), c.getZipCode())
                .setClient(client);
        client.setAddress(address);

        return client;
    }
}
