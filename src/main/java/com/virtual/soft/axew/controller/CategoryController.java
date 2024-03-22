package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.category.CategoryDto;
import com.virtual.soft.axew.dto.category.CategoryPageDto;
import com.virtual.soft.axew.dto.category.CategorySaveDto;
import com.virtual.soft.axew.entity.Category;
import com.virtual.soft.axew.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    @Operation(summary = "Get a paginated list of categories")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Categories paginated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryPageDto.class))})})
    public ResponseEntity<CategoryPageDto> findAll(
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<Category> categories = service.findAll(page, size);
        CategoryPageDto dto = new CategoryPageDto()
                .categories(categories.getContent()
                        .stream()
                        .map(CategoryDto::new)
                        .collect(Collectors.toList()))
                .totalPages(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .numberOfElements(categories.getNumberOfElements());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get category by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Category data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDto.class))})
    })
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        Category category = service.findById(id);
        CategoryDto dto = new CategoryDto(category);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @Operation(summary = "Search categories by name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Categories paginated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryPageDto.class))})})
    public ResponseEntity<CategoryPageDto> findByName(
            @RequestParam(name = "name") String name,
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Category> categories = service.findByName(name, page, size);
        CategoryPageDto dto = new CategoryPageDto()
                .categories(categories.getContent()
                        .stream()
                        .map(CategoryDto::new)
                        .collect(Collectors.toList()))
                .totalPages(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .numberOfElements(categories.getNumberOfElements());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping({"", ""})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Save a new category")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Category data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDto.class))})})
    public ResponseEntity<CategoryDto> save(@Valid @RequestBody CategorySaveDto newCategory) {
        Category category = service.convertFromDto(newCategory);
        Category categorySaved = service.save(category);
        CategoryDto dto = new CategoryDto(categorySaved);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
