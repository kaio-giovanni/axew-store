package com.virtual.soft.axew.service;

import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.model.Product;
import com.virtual.soft.axew.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findAll(pageable);
        return products.getContent();
    }

    public Product findById (Long id) {
        var product = repository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

    public List<Product> findByName (String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findByName(name, pageable);
        return products.getContent();
    }

}
