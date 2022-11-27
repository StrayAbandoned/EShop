package ru.lapshina.carts.sevice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapshina.api.ItemNotFound;
import ru.lapshina.api.ProductDto;
import ru.lapshina.carts.integration.ProductServiceIntegration;
import ru.lapshina.carts.model.Cart;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            carts.put(cartId, new Cart());
        }
        return carts.get(cartId);
    }

    public void add(String cartId, Long id) {
        ProductDto p = productServiceIntegration.getProductByID(id);
        getCart(cartId).add(p);
    }

    public void remove(String cartId, Long id) {
        getCart(cartId).remove(id);
    }

    public void clear(String cartId) {
        getCart(cartId).clear();
    }
}
