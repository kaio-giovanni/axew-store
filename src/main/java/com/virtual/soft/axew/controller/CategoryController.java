package com.virtual.soft.axew.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)))})})
    public ResponseEntity<List<Category>> findAll () {
        List<Category> categories = service.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get category by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Category data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Category.class))})
    })
    public ResponseEntity<Category> findById (@PathVariable Long id) {
        var category = service.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
