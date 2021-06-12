package com.virtual.soft.axew.service;

import com.virtual.soft.axew.dto.product.ProductSaveDto;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.entity.Category;
import com.virtual.soft.axew.entity.Product;
import com.virtual.soft.axew.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Page<Product> findAll (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findAll(pageable);
        return new PageImpl<>(products.getContent(), pageable, products.getTotalElements());
    }

    public Product findById (Long id) {
        var product = repository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

    public Page<Product> findByName (String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findByName(name, pageable);
        return new PageImpl<>(products.getContent(), pageable, products.getTotalElements());
    }

    public Product save (Product p) {
        return repository.save(p);
    }

    public Product fromDto (ProductSaveDto p) {
        return new Product()
                .setName(p.getName())
                .setDescription(p.getDescription())
                .setPrice(p.getPrice())
                .setImgUrl(p.getImgUrl())
                .setCategories(p.getCategoryIds()
                        .stream()
                        .map(categoryId -> new Category(categoryId, null))
                        .collect(Collectors.toSet()));
    }

}
