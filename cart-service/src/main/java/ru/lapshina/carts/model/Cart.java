package ru.lapshina.carts.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.lapshina.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Cart {

    private List<CartItem> list;
    private BigDecimal total;

    public Cart() {
        this.list = new ArrayList<>();
    }


    public void add(ProductDto product) {
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
        total = BigDecimal.valueOf(0);
        for (CartItem c : list) {
            total = total.add(c.getTotal());
        }
    }

    public void remove(Long productId) {
        if (list.removeIf(i-> i.getId().equals(productId))){
            countTotal();
        }
    }

    public void clear() {
        list.clear();
        total = BigDecimal.valueOf(0);
    }
}
