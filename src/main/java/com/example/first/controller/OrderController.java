package com.example.first.controller;

import com.example.first.repository.OrderRepository;
import com.example.first.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

}
