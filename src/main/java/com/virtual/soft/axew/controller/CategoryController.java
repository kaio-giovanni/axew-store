package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.category.CategoryDto;
import com.virtual.soft.axew.dto.category.CategorySaveDto;
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
    @Operation(summary = "Get a paginated list of categories")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "List of categories",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))})})
    public ResponseEntity<List<CategoryDto>> findAll (
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        List<Category> categories = service.findAll(page, size);
        List<CategoryDto> dto = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get category by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Category data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDto.class))})
    })
    public ResponseEntity<CategoryDto> findById (@PathVariable Long id) {
        Category category = service.findById(id);
        CategoryDto dto = new CategoryDto(category);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/filter")
    @Operation(summary = "Filter categories by name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "List of categories",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))})})
    public ResponseEntity<List<CategoryDto>> listByName (
            @RequestParam(name = "name") String name,
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size) {
        List<Category> categories = service.findByName(name, page, size);
        List<CategoryDto> dtos = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping({"", ""})
    @Operation(summary = "Save a new category")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Category data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDto.class))})})
    public ResponseEntity<CategoryDto> save (@Valid @RequestBody CategorySaveDto newCategory) {
        Category category = service.convertFromDto(newCategory);
        Category categorySave = service.save(category);
        CategoryDto dto = new CategoryDto(categorySave);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
