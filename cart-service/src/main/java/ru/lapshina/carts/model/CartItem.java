package ru.lapshina.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long id;
    private String title;
    private BigDecimal cost;
    private int count;
    private BigDecimal total;


    public void changeCount(int diff) {
        count += diff;
        total = cost.multiply(BigDecimal.valueOf(count));
    }

}
