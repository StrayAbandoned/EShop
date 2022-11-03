package ru.lapshina.api;

import java.util.List;

public class CartDto {

    private List<CartItemDto> list;
    private int total;


    public List<CartItemDto> getList() {
        return list;
    }

    public void setList(List<CartItemDto> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
