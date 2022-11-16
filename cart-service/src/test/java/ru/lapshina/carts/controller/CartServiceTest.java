package ru.lapshina.carts.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.lapshina.api.ProductDto;
import ru.lapshina.carts.integration.ProductServiceIntegration;
import ru.lapshina.carts.model.Cart;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
public class CartServiceTest {
    @MockBean
    private ProductServiceIntegration productServiceIntegration;


    @Test
    public void addTest(){
        ProductDto p = new ProductDto(7l, "Kiwi", BigDecimal.valueOf(100));
        Cart cart = new Cart();
        Mockito.doReturn(p).when(productServiceIntegration).getProductByID(7L);
        cart.add(p);
        Assertions.assertEquals(1, cart.getList().size());


    }

}
