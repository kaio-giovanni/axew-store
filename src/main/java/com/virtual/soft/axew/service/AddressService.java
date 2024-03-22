package com.virtual.soft.axew.service;

import com.virtual.soft.axew.dto.address.AddressSaveDto;
import com.virtual.soft.axew.entity.Address;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Address save(Address address) {
        return repository.save(address);
    }

    public Page<Address> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addresses = repository.findAll(pageable);
        return new PageImpl<>(addresses.getContent(), pageable, addresses.getTotalElements());
    }

    public Address findById(Long id) {
        Optional<Address> addressOpt = repository.findById(id);
        return addressOpt.orElseThrow(() -> new ResourceNotFoundException("Address not found."));
    }

    public List<Address> findAddress(String street, String number, String district, String zipCode) {
        return repository.findAddress(street, number, district, zipCode);
    }

    public Address convertFromDto(AddressSaveDto dto) {
        return new Address(dto.getStreet(), dto.getNumber(), dto.getDistrict(), dto.getZipCode());
    }
}
