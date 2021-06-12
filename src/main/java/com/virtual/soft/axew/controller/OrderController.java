package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.order.OrderDto;
import com.virtual.soft.axew.model.Order;
import com.virtual.soft.axew.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get order by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Order data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDto.class))})})
    public ResponseEntity<OrderDto> findById (@PathVariable Long id) {
        Order order = service.findById(id);
        OrderDto dto = new OrderDto(order);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
