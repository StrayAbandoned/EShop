package ru.lapshina.api;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private List<CartItemDto> list;
    private BigDecimal total;


    public List<CartItemDto> getList() {
        return list;
    }

    public void setList(List<CartItemDto> list) {
        this.list = list;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
