package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.ProductDto;
import com.virtual.soft.axew.model.Product;
import com.virtual.soft.axew.service.ProductService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "")
    @Operation(summary = "Get all products")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "List of products",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})})
    public ResponseEntity<List<ProductDto>> findAll () {
        List<Product> products = service.findAll();
        List<ProductDto> dto = products.stream().map(ProductDto::new).collect(Collectors.toList());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get product by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Product data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))})})
    public ResponseEntity<ProductDto> findById (@PathVariable Long id) {
        var product = service.findById(id);
        var dto = new ProductDto(product);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
