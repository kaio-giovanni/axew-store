package com.virtual.soft.axew.service;

import com.virtual.soft.axew.entity.Address;
import com.virtual.soft.axew.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Transactional
    public void save (Address address) {
        repository.save(address);
    }
}
