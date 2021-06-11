package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.client.ClientDto;
import com.virtual.soft.axew.dto.client.ClientSaveDto;
import com.virtual.soft.axew.model.Client;
import com.virtual.soft.axew.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping(value = "")
    @Operation(summary = "Get a paginated list of users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "List of users",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))})})
    public ResponseEntity<List<ClientDto>> findByPagination (
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<Client> clients = service.findAll(page, size);
        List<ClientDto> dto = clients.stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());

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
        Client clientSave = service.save(client);
        ClientDto dto = new ClientDto(clientSave);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
