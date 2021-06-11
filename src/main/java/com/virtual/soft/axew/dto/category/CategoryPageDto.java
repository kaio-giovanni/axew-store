package com.virtual.soft.axew.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CategoryPageDto {

    @Schema
    private List<CategoryDto> categories;

    @Schema(example = "20")
    private int totalPages;

    @Schema(example = "200")
    private long totalElements;

    @Schema(example = "10")
    private long numberOfElements;

    public CategoryPageDto () {
        // Do nothing
    }

    public List<CategoryDto> getCategories () {
        return categories;
    }

    public int getTotalPages () {
        return totalPages;
    }

    public long getTotalElements () {
        return totalElements;
    }

    public long getNumberOfElements () {
        return numberOfElements;
    }

    public CategoryPageDto categories (List<CategoryDto> categories) {
        this.categories = categories;
        return this;
    }

    public CategoryPageDto totalPages (int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public CategoryPageDto totalElements (long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public CategoryPageDto numberOfElements (long numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }
}
