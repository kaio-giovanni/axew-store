package com.virtual.soft.axew.service;

import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.model.Product;
import com.virtual.soft.axew.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll () {
        return repository.findAll();
    }

    public Product findById (Long id) {
        var product = repository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

}
