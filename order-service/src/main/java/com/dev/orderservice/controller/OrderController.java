package com.dev.orderservice.controller;

import com.dev.orderservice.dto.OrderRequest;
import com.dev.orderservice.model.Order;
import com.dev.orderservice.repository.OrderRepository;
import com.dev.orderservice.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

    @GetMapping
    public List<Order> getFindAllOrders(){
        return orderRepository.findAll();
    }


}
