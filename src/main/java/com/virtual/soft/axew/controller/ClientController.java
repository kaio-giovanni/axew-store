package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.ClientDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping(value = "")
    @Operation(summary = "Get a list of users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "List of users",
            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))})})
    public ResponseEntity<List<ClientDto>> findAll () {
        List<Client> clients = service.findAll();
        List<ClientDto> dto = clients.stream().map(ClientDto::new).collect(Collectors.toList());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get user by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User data",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))})})
    public ResponseEntity<ClientDto> findById (@PathVariable Long id) {
        var client = service.findById(id);
        var dto = new ClientDto(client);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
