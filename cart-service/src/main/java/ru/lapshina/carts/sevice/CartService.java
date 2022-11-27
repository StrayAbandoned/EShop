package ru.lapshina.carts.sevice;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.lapshina.api.ItemNotFound;
import ru.lapshina.api.ProductDto;
import ru.lapshina.carts.integration.ProductServiceIntegration;
import ru.lapshina.carts.model.Cart;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;


    public Cart getCart(String cartId) {
        if (!redisTemplate.hasKey(cartId)) {
            redisTemplate.opsForValue().set(cartId, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartId);
    }

    public void add(String cartId, Long id) {
        ProductDto p = productServiceIntegration.getProductByID(id);
        execute(cartId, cart -> cart.add(p));
    }

    public void remove(String cartId, Long id) {
        execute(cartId, cart -> cart.remove(id));
    }

    public void clear(String cartId) {
        execute(cartId, cart -> cart.clear());

    }

    private void execute(String cartId, Consumer<Cart> operation){
        Cart cart = getCart(cartId);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartId, cart);
    }
}
