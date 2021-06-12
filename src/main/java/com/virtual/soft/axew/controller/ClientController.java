package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.client.ClientDto;
import com.virtual.soft.axew.dto.client.ClientPageDto;
import com.virtual.soft.axew.dto.client.ClientSaveDto;
import com.virtual.soft.axew.model.Client;
import com.virtual.soft.axew.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping(value = "")
    @Operation(summary = "Get a paginated list of users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Users paginated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientPageDto.class))})})
    public ResponseEntity<ClientPageDto> findAll (
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Client> clients = service.findAll(page, size);
        ClientPageDto dto = new ClientPageDto()
                .clients(clients.getContent()
                        .stream()
                        .map(ClientDto::new)
                        .collect(Collectors.toList()))
                .totalPages(clients.getTotalPages())
                .totalElements(clients.getTotalElements())
                .numberOfElements(clients.getTotalElements());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get user by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientDto.class))})})
    public ResponseEntity<ClientDto> findById (@PathVariable Long id) {
        Client client = service.findById(id);
        ClientDto dto = new ClientDto(client);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping({"", ""})
    @Operation(summary = "Save new client")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Client data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientDto.class))})})
    public ResponseEntity<ClientDto> save (@Valid @RequestBody ClientSaveDto newClient) {
        Client client = service.convertFromDto(newClient);
        Client clientSaved = service.save(client);
        ClientDto dto = new ClientDto(clientSaved);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
