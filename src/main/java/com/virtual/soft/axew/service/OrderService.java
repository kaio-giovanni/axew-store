package com.virtual.soft.axew.service;

import com.virtual.soft.axew.dto.order.OrderSaveDto;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.entity.Order;
import com.virtual.soft.axew.entity.OrderItem;
import com.virtual.soft.axew.entity.Product;
import com.virtual.soft.axew.entity.enums.OrderStatus;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ClientService clientService;
    private final ProductService productService;

    public OrderService(OrderRepository repository, ClientService clientService, ProductService productService) {
        this.repository = repository;
        this.clientService = clientService;
        this.productService = productService;
    }

    public Order findById(Long id) {
        var order = repository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException("Order not found."));
    }

    @Transactional
    public Order save(Order order) {
        return repository.save(order);
    }

    public Order convertFromDto(OrderSaveDto dto) {
        Client client = clientService.findById(dto.getClientId());
        OrderStatus orderStatus = OrderStatus.toEnum(dto.getOrderStatus());
        Order order = new Order(dto.getMoment(), orderStatus, client);

        Set<OrderItem> orderItems = dto.getItems().stream().map(item -> {
            Product product = productService.findById(item.getProductId());
            return new OrderItem(order, product, item.getQuantity(), item.getPrice());
        }).collect(Collectors.toSet());

        order.setItems(orderItems);
        return order;
    }

}

