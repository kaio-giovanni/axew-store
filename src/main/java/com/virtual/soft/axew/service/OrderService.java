package com.virtual.soft.axew.service;

import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.entity.Order;
import com.virtual.soft.axew.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll () {
        return repository.findAll();
    }

    public Order findById (Long id) {
        var order = repository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException("Order not found."));
    }
}

