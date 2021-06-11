package com.virtual.soft.axew.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategorySaveDto {

    @Schema(example = "Technology")
    @NotEmpty(message = "Category name cannot be empty")
    @Size(min = 5, max = 60, message = "Category name must be have 5-60 characters")
    private String name;

    public CategorySaveDto () {
        // Do nothing
    }

    public String getName () {
        return name;
    }
}
