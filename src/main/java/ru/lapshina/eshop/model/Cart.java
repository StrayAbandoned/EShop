package ru.lapshina.eshop.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.lapshina.eshop.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Cart {

    private List<CartItem> list;
    private int total;

    public Cart() {
        this.list = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(list);
    }

    public void add(Product product) {
        for (CartItem i: list){
            if(product.getId().equals(i.getId())){
                i.changeCount(1);
                countTotal();
                return;
            }
        }
        list.add(new CartItem(product.getId(), product.getTitle(), product.getCost(), 1, product.getCost()));
        countTotal();

    }

    private void countTotal() {
        total = 0;
        for (CartItem c : list) {
            total += c.getTotal();
        }
    }

    public void remove(Long productId) {
        if (list.removeIf(i-> i.getId().equals(productId))){
            countTotal();
        }
    }

    public void clear() {
        list.clear();
        total = 0;
    }
}
