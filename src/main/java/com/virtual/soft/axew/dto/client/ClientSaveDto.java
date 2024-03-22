package com.virtual.soft.axew.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class ClientSaveDto {

    @Schema(example = "Client name")
    @NotEmpty
    private String name;

    @Schema(example = "000.000.000-00")
    @CPF
    private String cpf;

    @Schema(example = "myemail@test.com")
    @NotEmpty
    private String email;

    @Schema(example = "my_password")
    @Size(min = 5, max = 30)
    private String password;

    @Schema(example = "+55 00 99999-9999")
    @Size(min = 15, max = 17)
    private String phone;

    @Schema(example = "2000-01-01")
    @NotNull
    private LocalDate birthDate;

    @Schema(example = "10")
    @NotNull
    private Long addressId;

    @Schema(example = "[\"ROLE_ADMIN\",\"ROLE_USER\"]")
    @NotNull
    private Set<String> roles;

    public ClientSaveDto() {
        // Do nothing
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

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
