package com.virtual.soft.axew.service;

import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.model.Category;
import com.virtual.soft.axew.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll () {
        return repository.findAll();
    }

    public Category findById (Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException("Category not found."));
    }
}
