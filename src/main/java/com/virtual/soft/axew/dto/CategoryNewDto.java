package com.virtual.soft.axew.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class CategoryNewDto {

    @NotNull
    @Schema(example = "Technology")
    private final String name;
    
    public CategoryNewDto (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

}
