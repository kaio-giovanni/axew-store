package com.virtual.soft.axew.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

public class AddressSaveDto {

    @Schema(example = "Street one")
    @NotEmpty
    private String street;

    @Schema(example = "1010")
    @NotEmpty
    private String number;

    @Schema(example = "District one")
    @NotEmpty
    private String district;

    @Schema(example = "8008-080")
    @NotEmpty
    private String zipCode;

    public AddressSaveDto() {
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getDistrict() {
        return district;
    }

    public String getZipCode() {
        return zipCode;
    }

}
