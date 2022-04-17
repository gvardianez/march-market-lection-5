package ru.geekbrains.march.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.api.OrderItemDto;
import ru.geekbrains.march.market.core.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order) {
        List<OrderItemDto> orderItemDtoList = order.getItems()
                .stream()
                .map(orderItemConverter::entityToDto)
                .collect(Collectors.toList());
        return new OrderDto(order.getId(), order.getUser().getUsername(), orderItemDtoList, order.getTotalPrice());
    }

}
