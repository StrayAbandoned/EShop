package ru.lapshina.eshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.lapshina.api.CartDto;
import ru.lapshina.api.CartItemDto;
import ru.lapshina.eshop.entity.Order;
import ru.lapshina.eshop.entity.Product;
import ru.lapshina.eshop.integration.CartServiceIntegration;
import ru.lapshina.eshop.repository.OrderRepository;
import ru.lapshina.eshop.service.OrderService;
import ru.lapshina.eshop.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;
    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;
    @Test
    public void createOrderTest(){
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto= new CartItemDto();
        cartItemDto.setTitle("Juice");
        cartItemDto.setCost(BigDecimal.valueOf(20.5));
        cartItemDto.setCount(2);
        cartItemDto.setId(12L);
        cartItemDto.setTotal(BigDecimal.valueOf(41));
        List<CartItemDto> items = new ArrayList<>();
        items.add(cartItemDto);
        cartDto.setList(items);
        cartDto.setTotal(BigDecimal.valueOf(41));
        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCart();
        Product product = new Product(12L, "Juice", BigDecimal.valueOf(20.5));
        Mockito.doReturn(Optional.of(product)).when(productService).findById(12L);
        Order order = orderService.createOrder("Anastasia");
        Assertions.assertEquals(order.getTotalPrice(),BigDecimal.valueOf(41));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());

    }
}
