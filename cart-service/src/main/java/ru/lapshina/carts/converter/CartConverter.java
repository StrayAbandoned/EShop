package ru.lapshina.carts.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lapshina.api.CartDto;
import ru.lapshina.api.CartItemDto;
import ru.lapshina.carts.model.Cart;
import ru.lapshina.carts.model.CartItem;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;
    public CartDto modelToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setTotal(cart.getTotal());
        cartDto.setList(cart.getList().stream().map(cartItemConverter::modelToDto).collect(Collectors.toList()));

        return cartDto;

    }
}
