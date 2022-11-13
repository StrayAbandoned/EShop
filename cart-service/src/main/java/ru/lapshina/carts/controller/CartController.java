package ru.lapshina.carts.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.api.CartDto;
import ru.lapshina.carts.converter.CartConverter;
import ru.lapshina.carts.sevice.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public CartDto getCart() {
        return cartConverter.modelToDto(cartService.getCart());
    }

    @GetMapping("/clear")
    public void clear(){
        cartService.clear();
    }

    @GetMapping("/delete/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }
}
