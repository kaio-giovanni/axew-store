package com.virtual.soft.axew.controller;

import com.virtual.soft.axew.dto.product.ProductDto;
import com.virtual.soft.axew.dto.product.ProductPageDto;
import com.virtual.soft.axew.dto.product.ProductSaveDto;
import com.virtual.soft.axew.entity.Product;
import com.virtual.soft.axew.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "")
    @Operation(summary = "Get a paginate list of products")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Products paginated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductPageDto.class))})})
    public ResponseEntity<ProductPageDto> findAll (
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<Product> products = service.findAll(page, size);
        ProductPageDto dto = new ProductPageDto()
                .products(products.getContent()
                        .stream().map(ProductDto::new)
                        .collect(Collectors.toList()))
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .numberOfElements(products.getNumberOfElements());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get product by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Product data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))})})
    public ResponseEntity<ProductDto> findById (@PathVariable Long id) {
        Product product = service.findById(id);
        ProductDto dto = new ProductDto(product);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @Operation(summary = "Search products by name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Products paginated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductPageDto.class))})})
    public ResponseEntity<ProductPageDto> findByName (
            @RequestParam(name = "name") String name,
            @Schema(example = "1")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Schema(example = "10")
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<Product> products = service.findByName(name, page, size);
        ProductPageDto dto = new ProductPageDto()
                .products(products.getContent()
                        .stream().map(ProductDto::new)
                        .collect(Collectors.toList()))
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .numberOfElements(products.getNumberOfElements());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping({"", ""})
    @Operation(summary = "Save a new Product")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Product saved data",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))})})
    public ResponseEntity<ProductDto> save (@Valid @RequestBody ProductSaveDto newProduct) {
        Product product = service.fromDto(newProduct);
        Product productSaved = service.save(product);
        ProductDto dto = new ProductDto(productSaved);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
