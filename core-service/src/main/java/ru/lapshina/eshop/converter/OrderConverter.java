package ru.lapshina.eshop.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lapshina.api.OrderDto;
import ru.lapshina.api.ProductDto;
import ru.lapshina.eshop.entity.Order;
import ru.lapshina.eshop.entity.Product;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto (Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        orderDto.setUsername(order.getUsername());
        orderDto.setTotalPrice(order.getTotalPrice());
        return orderDto;

    }

}
