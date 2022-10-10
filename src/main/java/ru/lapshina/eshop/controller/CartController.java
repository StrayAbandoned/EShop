package ru.lapshina.eshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.eshop.dto.Cart;
import ru.lapshina.eshop.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart getCart() {
        return cartService.getCart();
    }

    @GetMapping("/delete/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }
}
