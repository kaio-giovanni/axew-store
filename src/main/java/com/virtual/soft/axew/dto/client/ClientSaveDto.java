package com.virtual.soft.axew.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ClientSaveDto {

    @Schema(example = "Client name")
    @NotNull
    private String name;

    @Schema(example = "000.000.000-00")
    @Size(min = 14, max = 14, message = "CPF must be have format ###.###.###-##")
    private String cpf;

    @Schema(example = "myemail@test.com")
    @NotNull
    private String email;

    @Schema(example = "mypassword")
    @Size(min = 5, max = 30, message = "Password must be have 5-30 characters")
    private String password;

    @Schema(example = "+55 99 99999-9999")
    @Size(min = 17, max = 17, message = "Phone must be have format +## ## #####-####")
    private String phone;

    @Schema(example = "2000-01-01")
    @NotNull
    private LocalDate birthDate;

    @Schema(example = "Street one")
    @NotNull
    private String street;

    @Schema(example = "105A")
    @NotNull
    private String number;

    @Schema(example = "district one")
    @NotNull
    private String district;

    @Schema(example = "00000-000")
    @NotNull
    private String zipCode;

    public ClientSaveDto () {
        // Do nothing
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

    public String getPassword () {
        return password;
    }

    public String getPhone () {
        return phone;
    }

    public LocalDate getBirthDate () {
        return birthDate;
    }

    public String getStreet () {
        return street;
    }

    public String getNumber () {
        return number;
    }

    public String getDistrict () {
        return district;
    }

    public String getZipCode () {
        return zipCode;
    }
}
