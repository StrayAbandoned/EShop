package ru.lapshina.eshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.lapshina.eshop.entity.Order;
import ru.lapshina.eshop.entity.OrderItem;
import ru.lapshina.eshop.entity.User;
import ru.lapshina.eshop.model.Cart;
import ru.lapshina.eshop.repository.OrderItemRepository;
import ru.lapshina.eshop.repository.OrderRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableTransactionManagement(proxyTargetClass = true)
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;

    private final OrderRepository repository;
    private final OrderItemRepository itemRepository;

    @Transactional
    public void createOrder(User user) {
        Cart cart = cartService.getCart();
        Order order = new Order();
        List<OrderItem> orderItem = cart.getItems().stream().map(cartItem -> new OrderItem(
                productService.findById(cartItem.getId()).get(),
                order,
                cartItem.getCount(),
                cartItem.getCost(),
                cartItem.getTotal())).collect(Collectors.toList());
        order.setUser(user);
        order.setItems(orderItem);
        order.setTotalPrice(countPrice(orderItem));
        repository.save(order);
        for (OrderItem item: orderItem){
            itemRepository.save(item);
        }

    }

    private Integer countPrice(List<OrderItem> orderItems){
        Integer total = 0;
        for(OrderItem item: orderItems){
            total+=item.getTotalCost();
        }
        return total;
    }

}
