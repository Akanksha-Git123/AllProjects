package com.shopappln.com.repository;

import com.shopappln.com.entity.Order;
import com.shopappln.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
