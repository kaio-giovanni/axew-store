package com.virtual.soft.axew.dto.client;

import com.virtual.soft.axew.dto.address.AddressDto;
import com.virtual.soft.axew.model.Client;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class ClientDto {

    @Schema(example = "1")
    private final Long id;

    @Schema(example = "User name")
    private final String name;

    @Schema(example = "000.000.000-00")
    private final String cpf;

    @Schema(example = "User email")
    private final String email;

    @Schema(example = "12334556")
    private final String phone;

    @Schema(example = "2000-01-01")
    private final LocalDate birthDate;

    @Schema
    private final AddressDto addressDto;

    public ClientDto (Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        email = client.getEmail();
        phone = client.getPhone();
        birthDate = client.getBirthDate();
        addressDto = new AddressDto(client.getAddress());
    }

    public Long getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public String getCpf () {
        return cpf;
    }

    public String getEmail () {
        return email;
    }

    public String getPhone () {
        return phone;
    }

    public LocalDate getBirthDate () {
        return birthDate;
    }

    public AddressDto getAddressDto () {
        return addressDto;
    }
}
