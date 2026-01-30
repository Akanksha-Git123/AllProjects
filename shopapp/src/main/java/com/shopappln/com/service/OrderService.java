package com.shopappln.com.service;

import com.shopappln.com.dto.CreateOrderRequest;
import com.shopappln.com.dto.OrderHistoryResponse;
import com.shopappln.com.entity.*;
import com.shopappln.com.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo,
                        UserRepository userRepo,
                        ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public Order createOrder(CreateOrderRequest request) {

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);

        double total = 0;

        for (CreateOrderRequest.Item req : request.getItems()) {

            Product product = productRepo.findById(req.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(req.getQuantity());
            item.setPrice(product.getPrice());

            total += product.getPrice() * req.getQuantity();
            order.getItems().add(item);
        }

        order.setTotalAmount(total);
        return orderRepo.save(order);
    }

    // ✅ ORDER HISTORY (DTO SAFE)
    public List<OrderHistoryResponse> getOrdersByUser(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepo.findByUser(user).stream().map(order -> {

            OrderHistoryResponse res = new OrderHistoryResponse();
            res.setId(order.getId());
            res.setTotalAmount(order.getTotalAmount());
            res.setStatus(order.getStatus());
            res.setCreatedAt(order.getCreatedAt());

            var items = order.getItems().stream().map(oi -> {
                OrderHistoryResponse.Item i = new OrderHistoryResponse.Item();
                i.setProductName(oi.getProduct().getName());
                i.setQuantity(oi.getQuantity());
                i.setPrice(oi.getPrice());
                return i;
            }).toList();

            res.setItems(items);
            return res;

        }).toList();
    }
}
