package com.virtual.soft.axew.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class AddressPageDto {
    @Schema
    private List<AddressDto> addresses;

    @Schema(example = "20")
    private int totalPages;

    @Schema(example = "200")
    private long totalElements;

    @Schema(example = "10")
    private long numberOfElements;

    public AddressPageDto() {
        // Do nothing
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public AddressPageDto addresses(List<AddressDto> addresses) {
        this.addresses = addresses;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public AddressPageDto totalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public AddressPageDto totalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public AddressPageDto numberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }
}
