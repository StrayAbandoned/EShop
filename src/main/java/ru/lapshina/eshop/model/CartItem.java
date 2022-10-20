package ru.lapshina.eshop.model;

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
    private Integer cost;
    private int count;
    private int total;


    public void changeCount(int diff) {
        count += diff;
        total = cost * count;
    }

}
