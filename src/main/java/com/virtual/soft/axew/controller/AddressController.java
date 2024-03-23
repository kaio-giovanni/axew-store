package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.address.AddressDto;
import com.virtual.soft.axew.dto.address.AddressPageDto;
import com.virtual.soft.axew.dto.address.AddressSaveDto;
import com.virtual.soft.axew.entity.Address;
import com.virtual.soft.axew.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get a paginated list of addresses")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Addresses paginated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AddressPageDto.class))})})
    public ResponseEntity<AddressPageDto> findAll(
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Address> addresses = service.findAll(page, size);
        AddressPageDto dto = new AddressPageDto()
                .addresses(addresses.getContent()
                        .stream()
                        .map(AddressDto::new)
                        .collect(Collectors.toList()))
                .totalPages(addresses.getTotalPages())
                .totalElements(addresses.getTotalElements())
                .numberOfElements(addresses.getTotalElements());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping({"/save"})
    @Operation(summary = "Save a new address")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Address data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AddressDto.class))})})
    public ResponseEntity<AddressDto> save(@Valid @RequestBody AddressSaveDto addressSaveDto) {
        Address address = service.convertFromDto(addressSaveDto);
        Address addressSaved = service.save(address);
        AddressDto dto = new AddressDto(addressSaved);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
