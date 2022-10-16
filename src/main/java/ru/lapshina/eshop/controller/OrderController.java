package ru.lapshina.eshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.eshop.entity.User;
import ru.lapshina.eshop.service.OrderService;
import ru.lapshina.eshop.service.UserService;

import java.security.Principal;


@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public void createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        orderService.createOrder(user);
    }
}
