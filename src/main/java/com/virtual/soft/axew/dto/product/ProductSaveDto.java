package com.virtual.soft.axew.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductSaveDto {

    @Schema(example = "Name of Product")
    @NotEmpty
    private String name;

    @Schema(example = "Description of Product")
    @NotEmpty
    @Size(min = 10)
    private String description;

    @Schema(example = "Price of Product")
    @NotNull
    private double price;

    @Schema(example = "Url image of Product")
    @NotEmpty
    private String imgUrl;

    @Schema(example = "List of categories of Product")
    @NotNull
    private List<Long> categoryIds;

    public ProductSaveDto () {
        // Do nothing
    }

    public String getName () {
        return name;
    }

    public String getDescription () {
        return description;
    }

    public double getPrice () {
        return price;
    }

    public String getImgUrl () {
        return imgUrl;
    }

    public List<Long> getCategoryIds () {
        return categoryIds;
    }
}
