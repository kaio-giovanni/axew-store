package com.virtual.soft.axew.dto;

import com.virtual.soft.axew.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;

public class AddressDto {

    @Schema(example = "10")
    private final Long id;

    @Schema(example = "Street one")
    private final String street;

    @Schema(example = "1010")
    private final String number;

    @Schema(example = "District one")
    private final String district;

    @Schema(example = "8008-080")
    private final String zipCode;

    public AddressDto (Address address) {
        id = address.getId();
        street = address.getStreet();
        number = address.getNumber();
        district = address.getDistrict();
        zipCode = address.getZipCode();
    }

    public Long getId () {
        return id;
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
