package com.virtual.soft.axew.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class OrderSaveDto {

    @Schema(hidden = true)
    private final Integer orderStatus;

    @Schema(hidden = true)
    private final Instant moment;

    @Schema(hidden = true)
    private final double total;

    @Schema()
    private Set<OrderItemSaveDto> items = new HashSet<>();

    @Schema(example = "10")
    private Long clientId;

    public OrderSaveDto() {
        this.orderStatus = 1;
        this.moment = Instant.now();
        this.total = getTotalPrice();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public Instant getMoment() {
        return moment;
    }

    public Set<OrderItemSaveDto> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public Long getClientId() {
        return clientId;
    }

    private double getTotalPrice() {
        double price = 0.0;
        for (OrderItemSaveDto dto : this.items) {
            price += dto.getPrice() * dto.getQuantity();
        }
        return price;
    }

    public static class OrderItemSaveDto {

        @Schema(example = "10")
        private Long productId;

        @Schema(example = "2")
        private Integer quantity;

        @Schema(example = "100")
        private Double price;

        public OrderItemSaveDto() {
            // Do nothing
        }

        public Long getProductId() {
            return productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Double getPrice() {
            return price;
        }
    }
}
