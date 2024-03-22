package com.virtual.soft.axew.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ProductPageDto {

    @Schema
    private List<ProductDto> products;

    @Schema(example = "20")
    private int totalPages;

    @Schema(example = "200")
    private long totalElements;

    @Schema(example = "10")
    private long numberOfElements;

    public ProductPageDto() {
        // Do nothing
    }

    public ProductPageDto products(List<ProductDto> products) {
        this.products = products;
        return this;
    }

    public ProductPageDto totalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public ProductPageDto totalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public ProductPageDto numberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }
}
