package ru.lapshina.eshop.dto;

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
        CartItem c = new CartItem(product.getId(), product.getTitle(), product.getCost(), 1, product.getCost());
        if (!list.contains(c)) {
            list.add(c);
        } else {
            int i = list.indexOf(c);
            int count = list.get(i).getCount() + 1;
            list.get(i).setCount(count);
            list.get(i).setTotal(count * list.get(i).getCost());
        }
        countTotal();

    }

    private void countTotal() {
        total = 0;
        for (CartItem c : list) {
            total += c.getTotal();
        }
    }

    public void remove(Product product) {
        CartItem c = new CartItem(product.getId(), product.getTitle(), product.getCost(), 1, product.getCost());
        if (!list.contains(c)) {
            return;
        } else {
            int i = list.indexOf(c);
            int count = list.get(i).getCount();
            if (count == 1) {
                list.remove(c);
            } else {
                count = list.get(i).getCount() - 1;
                list.get(i).setCount(count);
                list.get(i).setTotal(count * list.get(i).getCost());
            }
        }
        countTotal();
    }
}
