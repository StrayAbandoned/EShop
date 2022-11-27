package ru.lapshina.eshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.lapshina.api.CartDto;
import ru.lapshina.eshop.entity.Order;
import ru.lapshina.eshop.entity.OrderItem;
import ru.lapshina.eshop.integration.CartServiceIntegration;
import ru.lapshina.eshop.repository.OrderRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableTransactionManagement(proxyTargetClass = true)
public class OrderService {
    private final ProductService productService;

    private final OrderRepository repository;
    private final CartServiceIntegration cartServiceIntegration;


    @Transactional
    public Order createOrder(String username) {
        CartDto cart = cartServiceIntegration.getCart(username);
        Order order = new Order();
        List<OrderItem> orderItem = cart.getList().stream().map(cartItem -> new OrderItem(
                productService.findById(cartItem.getId()).get(),
                order,
                cartItem.getCount(),
                cartItem.getCost(),
                cartItem.getTotal())).collect(Collectors.toList());
        order.setUsername(username);
        order.setItems(orderItem);
        order.setTotalPrice(countPrice(orderItem));
        repository.save(order);
        return order;

    }

    private BigDecimal countPrice(List<OrderItem> orderItems){
        BigDecimal total = BigDecimal.valueOf(0);
        for(OrderItem item: orderItems){
            total= total.add(item.getTotalCost());
        }
        return total;
    }

}
