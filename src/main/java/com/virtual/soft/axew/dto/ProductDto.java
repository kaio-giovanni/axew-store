package com.virtual.soft.axew.dto;

import com.virtual.soft.axew.model.Product;
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

    public ProductDto () {
    }

    public ProductDto (Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public Double getPrice () {
        return price;
    }

    public void setPrice (Double price) {
        this.price = price;
    }

    public String getImgUrl () {
        return imgUrl;
    }

    public void setImgUrl (String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
