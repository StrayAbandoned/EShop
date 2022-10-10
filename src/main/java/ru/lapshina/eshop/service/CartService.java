package ru.lapshina.eshop.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lapshina.eshop.dto.Cart;
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
        Product p = productService.findById(id).orElseThrow(() -> new ItemNotFound("Product not found"));
        cart.remove(p);
    }
}
