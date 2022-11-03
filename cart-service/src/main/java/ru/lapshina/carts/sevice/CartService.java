package ru.lapshina.carts.sevice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapshina.api.ItemNotFound;
import ru.lapshina.api.ProductDto;
import ru.lapshina.carts.integration.ProductServiceIntegration;
import ru.lapshina.carts.model.Cart;


import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCart() {
        return this.cart;
    }

    public void add(Long id) {
        ProductDto p = productServiceIntegration.getProductByID(id).orElseThrow(() -> new ItemNotFound("Product not found"));
        cart.add(p);
    }

    public void remove(Long id) {
        cart.remove(id);
    }

    public void clear(){
        cart.clear();
    }
}
