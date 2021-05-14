package com.virtual.soft.axew.dto;

import com.virtual.soft.axew.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    @Schema(example = "10")
    private Long id;

    @Schema(example = "Technology")
    private String name;

    @Schema
    private List<ProductDto> products;

    public CategoryDto () {
    }

    public CategoryDto (Category category) {
        id = category.getId();
        name = category.getName();
        products = category.getProducts()
                .stream()
                .map(ProductDto::new).collect(Collectors.toList());
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

    public List<ProductDto> getProducts () {
        return products;
    }

    public void setProducts (List<ProductDto> products) {
        this.products = products;
    }
}
