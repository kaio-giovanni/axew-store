package com.virtual.soft.axew.dto.client;

import com.virtual.soft.axew.dto.address.AddressDto;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.entity.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;

@Schema(name = "Client")
public class ClientDto {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "User name")
    private String name;

    @Schema(example = "000.000.000-00")
    private String cpf;

    @Schema(example = "User email")
    private String email;

    @Schema(example = "12334556")
    private String phone;

    @Schema(example = "2000-01-01")
    private LocalDate birthDate;

    @Schema
    private AddressDto address;

    @Schema(example = "[\"ADMIN\",\"USER\"]")
    private Set<RoleEnum> roles;

    public ClientDto() {
        // Do nothing
    }

    public ClientDto(Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        email = client.getEmail();
        phone = client.getPhone();
        birthDate = client.getBirthDate();
        roles = client.getRoles();
        address = new AddressDto(client.getAddress());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public AddressDto getAddress() {
        return address;
    }
}
