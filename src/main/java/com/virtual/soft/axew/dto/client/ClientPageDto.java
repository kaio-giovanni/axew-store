package com.virtual.soft.axew.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ClientPageDto {

    @Schema
    private List<ClientDto> clients;

    @Schema(example = "20")
    private int totalPages;

    @Schema(example = "200")
    private long totalElements;

    @Schema(example = "10")
    private long numberOfElements;

    public ClientPageDto () {
        // Do nothing
    }

    public List<ClientDto> getClients () {
        return clients;
    }

    public ClientPageDto clients (List<ClientDto> clients) {
        this.clients = clients;
        return this;
    }

    public int getTotalPages () {
        return totalPages;
    }

    public ClientPageDto totalPages (int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public long getTotalElements () {
        return totalElements;
    }

    public ClientPageDto totalElements (long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public long getNumberOfElements () {
        return numberOfElements;
    }

    public ClientPageDto numberOfElements (long numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }
}
