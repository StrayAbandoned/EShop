package ru.lapshina.eshop.converter;

import org.springframework.stereotype.Component;
import ru.lapshina.api.OrderItemDto;
import ru.lapshina.api.ProductDto;
import ru.lapshina.eshop.entity.OrderItem;
import ru.lapshina.eshop.entity.Product;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto (OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        orderItemDto.setCost(orderItem.getCost());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setTotalCost(orderItem.getTotalCost());
        return orderItemDto;

    }

}
