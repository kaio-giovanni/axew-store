package com.virtual.soft.axew.dto;

import com.virtual.soft.axew.model.Client;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class ClientDto {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "User name")
    private String name;

    @Schema(example = "User email")
    private String email;

    @Schema(example = "12334556")
    private String phone;

    @Schema(example = "2000-01-01")
    private LocalDate birthDate;

    @Schema
    private AddressDto addressDto;

    public ClientDto (Client client) {
        id = client.getId();
        name = client.getName();
        email = client.getEmail();
        phone = client.getPhone();
        birthDate = client.getBirthDate();
        addressDto = new AddressDto(client.getAddress());
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate () {
        return birthDate;
    }

    public void setBirthDate (LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public AddressDto getAddress () {
        return addressDto;
    }

    public void setAddress (AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
