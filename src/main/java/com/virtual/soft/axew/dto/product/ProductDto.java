package com.virtual.soft.axew.dto.product;

import com.virtual.soft.axew.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProductDto {

    @Schema(example = "120")
    private Long id;

    @Schema(example = "Iphone 10")
    private String name;

    @Schema(example = "Apple Smartphone")
    private String description;

    @Schema(example = "2.000")
    private Double price;

    @Schema(example = "www.domain.com/img-url")
    private String imgUrl;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
