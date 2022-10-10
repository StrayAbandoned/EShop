package ru.lapshina.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long id;
    private String title;
    private int cost;
    private int count;
    private int total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return cost == cartItem.cost && Objects.equals(id, cartItem.id) && Objects.equals(title, cartItem.title);
    }

}
