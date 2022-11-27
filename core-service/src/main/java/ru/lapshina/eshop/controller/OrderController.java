package ru.lapshina.eshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.api.OrderDto;
import ru.lapshina.eshop.converter.OrderConverter;
import ru.lapshina.eshop.entity.Order;
import ru.lapshina.eshop.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;


    @PostMapping
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestHeader String username) {
        return orderService.getOrders(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
