package com.virtual.soft.axew.dto;

import com.virtual.soft.axew.model.Order;
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

    public void setId (Long id) {
        this.id = id;
    }

    public Instant getMoment () {
        return moment;
    }

    public void setMoment (Instant moment) {
        this.moment = moment;
    }

    public Integer getOrderStatus () {
        return orderStatus;
    }

    public void setOrderStatus (Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemDto> getItems () {
        return items;
    }

    public void setItems (List<OrderItemDto> items) {
        this.items = items;
    }

    public double getTotal () {
        return total;
    }

    public void setTotal (double total) {
        this.total = total;
    }

    public ClientDto getClient () {
        return client;
    }

    public void setClient (ClientDto client) {
        this.client = client;
    }
}
