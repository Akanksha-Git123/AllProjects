package com.shopappln.com.controller;

import com.shopappln.com.dto.CreateOrderRequest;
import com.shopappln.com.dto.OrderHistoryResponse;
import com.shopappln.com.entity.Order;
import com.shopappln.com.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest request) {
        return service.createOrder(request);
    }

    @GetMapping("/user/{userId}")
    public List<OrderHistoryResponse> getByUser(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }
}
