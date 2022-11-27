package ru.lapshina.carts.converter;

import org.springframework.stereotype.Component;
import ru.lapshina.api.CartItemDto;
import ru.lapshina.carts.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto modelToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setCost(cartItem.getCost());
        cartItemDto.setCount(cartItem.getCount());
        cartItemDto.setTitle(cartItem.getTitle());
        cartItemDto.setTotal(cartItem.getTotal());
        return cartItemDto;

    }
}
