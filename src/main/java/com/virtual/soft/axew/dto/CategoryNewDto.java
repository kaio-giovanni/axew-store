package com.virtual.soft.axew.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoryNewDto {

    @Schema(example = "Technology")
    private String name;

    public CategoryNewDto () {
    }

    public CategoryNewDto (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
