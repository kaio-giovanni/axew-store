package com.virtual.soft.axew.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategorySaveDto {

    @Schema(example = "Technology")
    @NotEmpty
    @Size(min = 5, max = 60)
    private String name;

    public CategorySaveDto() {
        // Do nothing
    }

    public String getName() {
        return name;
    }
}
