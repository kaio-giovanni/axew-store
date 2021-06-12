package com.virtual.soft.axew.dto.order;

import com.virtual.soft.axew.dto.client.ClientDto;
import com.virtual.soft.axew.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    @Schema(example = "120")
    private Long id;

    @Schema(example = "2021-01-01")
    private Instant moment;

    @Schema(example = "1")
    private Integer orderStatus;

    @Schema()
    private List<OrderItemDto> items = new ArrayList<>();

    @Schema(example = "200.00")
    private double total;

    @Schema()
    private ClientDto client;

    public OrderDto () {
    }

    public OrderDto (Order order) {
        id = order.getId();
        moment = order.getMoment();
        orderStatus = order.getOrderStatus().getCode();
        total = order.getTotal();
        client = new ClientDto(order.getClient());
        items = order.getItems().
                stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
    }

    public Long getId () {
        return id;
    }

    public Instant getMoment () {
        return moment;
    }

    public Integer getOrderStatus () {
        return orderStatus;
    }

    public List<OrderItemDto> getItems () {
        return items;
    }

    public double getTotal () {
        return total;
    }

    public ClientDto getClient () {
        return client;
    }
}
