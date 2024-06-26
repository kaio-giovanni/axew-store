package com.virtual.soft.axew.dto.category;

import com.virtual.soft.axew.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Category")
public class CategoryDto {

    @Schema(example = "10")
    private Long id;

    @Schema(example = "Technology")
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
