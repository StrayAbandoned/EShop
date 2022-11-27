package ru.lapshina.carts.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.api.CartDto;
import ru.lapshina.api.StringResponse;
import ru.lapshina.carts.converter.CartConverter;
import ru.lapshina.carts.sevice.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate")
    public StringResponse generateCartId(){
        return new StringResponse(UUID.randomUUID().toString());
    }



    @GetMapping("/{cartId}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable Long id, @PathVariable String cartId) {
        String targetId = getCartId(username, cartId);
        cartService.add(targetId, id);
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String cartId) {
        String targetId = getCartId(username, cartId);
        return cartConverter.modelToDto(cartService.getCart(targetId));
    }

    @GetMapping("/{cartId}/clear")
    public void clear(@RequestHeader(name = "username", required = false) String username, @PathVariable String cartId) {
        String targetId = getCartId(username, cartId);
        cartService.clear(targetId);
    }

    @GetMapping("/{cartId}/delete/{id}")
    public void removeFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String cartId, @PathVariable Long id) {
        String targetId = getCartId(username, cartId);
        cartService.remove(targetId, id);
    }

    private String getCartId(String username, String cartId){
        if(username!=null){
            return username;
        }
        return cartId;

    }
}
