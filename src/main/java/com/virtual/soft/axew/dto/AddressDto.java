package com.virtual.soft.axew.dto;

import com.virtual.soft.axew.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;

public class AddressDto {

    @Schema(example = "10")
    private Long id;

    @Schema(example = "Street one")
    private String street;

    @Schema(example = "1010")
    private String number;

    @Schema(example = "District one")
    private String district;

    @Schema(example = "8008-080")
    private String zipCode;

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

    public void setId (Long id) {
        this.id = id;
    }

    public String getStreet () {
        return street;
    }

    public void setStreet (String street) {
        this.street = street;
    }

    public String getNumber () {
        return number;
    }

    public void setNumber (String number) {
        this.number = number;
    }

    public String getDistrict () {
        return district;
    }

    public void setDistrict (String district) {
        this.district = district;
    }

    public String getZipCode () {
        return zipCode;
    }

    public void setZipCode (String zipCode) {
        this.zipCode = zipCode;
    }
}
