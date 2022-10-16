package ru.lapshina.eshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapshina.eshop.model.Cart;
import ru.lapshina.eshop.entity.Product;
import ru.lapshina.eshop.exception.ItemNotFound;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class CartService {
    private ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCart() {
        return this.cart;
    }

    public void add(Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ItemNotFound("Product not found"));
        cart.add(p);
    }

    public void remove(Long id) {
        cart.remove(id);
    }

    public void clear(){
        cart.clear();
    }
}
