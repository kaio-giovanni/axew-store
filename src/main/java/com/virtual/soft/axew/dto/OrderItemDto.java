package com.virtual.soft.axew.dto;

import com.virtual.soft.axew.model.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;

public class OrderItemDto {

    @Schema()
    private ProductDto product;

    @Schema(example = "2")
    private Integer quantity;

    @Schema(example = "100")
    private Double price;

    public OrderItemDto () {
    }

    public OrderItemDto (OrderItem orderItem) {
        product = new ProductDto(orderItem.getProduct());
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();
    }

    public ProductDto getProduct () {
        return product;
    }

    public void setProduct (ProductDto product) {
        this.product = product;
    }

    public Integer getQuantity () {
        return quantity;
    }

    public void setQuantity (Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice () {
        return price;
    }

    public void setPrice (Double price) {
        this.price = price;
    }
}
