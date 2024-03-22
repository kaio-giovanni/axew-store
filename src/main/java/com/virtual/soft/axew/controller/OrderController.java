package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.order.OrderDto;
import com.virtual.soft.axew.dto.order.OrderSaveDto;
import com.virtual.soft.axew.entity.Order;
import com.virtual.soft.axew.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(summary = "Get order by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Order data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDto.class))})})
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        Order order = service.findById(id);
        OrderDto dto = new OrderDto(order);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping({"/new"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(summary = "Create a new order")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Order data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDto.class))})})
    public ResponseEntity<OrderDto> makeOrder(@Valid @RequestBody OrderSaveDto orderSaveDto) {
        Order order = service.convertFromDto(orderSaveDto);
        Order savedOrder = service.save(order);
        OrderDto dto = new OrderDto(savedOrder);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
