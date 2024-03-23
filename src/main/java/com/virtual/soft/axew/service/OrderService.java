package com.virtual.soft.axew.service;

import com.virtual.soft.axew.dto.order.OrderDto;
import com.virtual.soft.axew.dto.order.OrderSaveDto;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.entity.Order;
import com.virtual.soft.axew.entity.OrderItem;
import com.virtual.soft.axew.entity.Product;
import com.virtual.soft.axew.entity.enums.OrderStatus;
import com.virtual.soft.axew.entity.enums.RoleEnum;
import com.virtual.soft.axew.exception.AuthorizationException;
import com.virtual.soft.axew.exception.ResourceNotFoundException;
import com.virtual.soft.axew.repository.OrderItemRepository;
import com.virtual.soft.axew.repository.OrderRepository;
import com.virtual.soft.axew.security.user.UserAuth;
import com.virtual.soft.axew.utils.UserAuthUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderItemRepository orderItemRepository;
    private final ClientService clientService;
    private final ProductService productService;

    public OrderService(OrderRepository repository,
                        OrderItemRepository orderItemRepository,
                        ClientService clientService,
                        ProductService productService) {
        this.repository = repository;
        this.orderItemRepository = orderItemRepository;
        this.clientService = clientService;
        this.productService = productService;
    }

    public Order findById(Long id) {
        Optional<Order> orderOpt = repository.findById(id);

        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            UserAuth userAuth = UserAuthUtils.getAuthenticatedUser();
            if (userAuth.getId().equals(order.getClient().getId()) || userAuth.hasRole(RoleEnum.ADMIN)) {
                return order;
            } else {
             throw new AuthorizationException("Access Denied!");
            }
        } else {
            throw new ResourceNotFoundException("Order not found.");
        }
    }

    @Transactional
    public Order save(Order order) {
        return repository.save(order);
    }

    @Transactional
    public OrderDto saveOrder(OrderSaveDto dto) {
        Client client = clientService.findById(dto.getClientId());
        OrderStatus orderStatus = OrderStatus.toEnum(dto.getOrderStatus());
        Order order = new Order(dto.getMoment(), orderStatus, client);
        this.save(order);

        Set<OrderItem> orderItems = dto.getItems().stream().map(item -> {
            Product product = productService.findById(item.getProductId());
            OrderItem orderItem = new OrderItem(order, product, item.getQuantity(), item.getPrice());
            return orderItemRepository.save(orderItem);
        }).collect(Collectors.toSet());

        order.setItems(orderItems);
        return new OrderDto(order);
    }

}

