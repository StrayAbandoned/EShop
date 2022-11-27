package ru.lapshina.carts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lapshina.api.ProductDto;

import java.math.BigDecimal;

@SpringBootTest
public class CartTest {

    @Spy
    private Cart cart;

    @Test
    public void addTest(){
        ProductDto productDto = new ProductDto(20L, "Tomato", BigDecimal.valueOf(30));
        cart.add(productDto);
        Mockito.verify(cart).add(productDto);
        Assertions.assertEquals(1,cart.getList().size());
    }
}
