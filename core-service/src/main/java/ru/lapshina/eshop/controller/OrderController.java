package ru.lapshina.eshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.eshop.service.OrderService;


@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }
}
