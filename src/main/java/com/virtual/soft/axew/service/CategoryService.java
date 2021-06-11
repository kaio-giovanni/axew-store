package com.virtual.soft.axew.service;

import com.virtual.soft.axew.dto.category.CategorySaveDto;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.model.Category;
import com.virtual.soft.axew.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = repository.findAll(pageable);
        return categories.getContent();
    }

    public Category findById (Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException("Category not found."));
    }

    public List<Category> findByName (String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = repository.findByName(name, pageable);
        return categories.getContent();
    }

    public Category save (Category c) {
        return repository.save(c);
    }

    public Category convertFromDto (CategorySaveDto dto) {
        return new Category(null, dto.getName());
    }
}
