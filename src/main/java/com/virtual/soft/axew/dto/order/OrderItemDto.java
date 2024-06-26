package com.virtual.soft.axew.dto.order;

import com.virtual.soft.axew.dto.product.ProductDto;
import com.virtual.soft.axew.entity.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "OrderItem")
public class OrderItemDto {

    @Schema()
    private ProductDto product;

    @Schema(example = "2")
    private Integer quantity;

    @Schema(example = "100")
    private Double price;

    public OrderItemDto() {
    }

    public OrderItemDto(OrderItem orderItem) {
        product = new ProductDto(orderItem.getProduct());
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();
    }

    public ProductDto getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }
}
