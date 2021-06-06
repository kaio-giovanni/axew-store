package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.CategoryDto;
import com.virtual.soft.axew.dto.CategoryNewDto;
import com.virtual.soft.axew.model.Category;
import com.virtual.soft.axew.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping(value = "")
    @Operation(summary = "Get a list of categories")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "List of categories",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))})})
    public ResponseEntity<List<CategoryDto>> findAll () {
        List<Category> categories = service.findAll();
        List<CategoryDto> dto = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping({"", ""})
    @Operation(summary = "Save a new category")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Category data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDto.class))})})
    public ResponseEntity<CategoryDto> save (@Valid @RequestBody CategoryNewDto newCategory) {
        var category = service.save(newCategory);
        var dto = new CategoryDto(category);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get category by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Category data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDto.class))})
    })
    public ResponseEntity<CategoryDto> findById (@PathVariable Long id) {
        var category = service.findById(id);
        var dto = new CategoryDto(category);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
