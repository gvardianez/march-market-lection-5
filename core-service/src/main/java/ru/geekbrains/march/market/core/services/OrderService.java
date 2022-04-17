package ru.geekbrains.march.market.core.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.entities.User;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public Order createNewOrder(User user, CartDto cartDto) {
        List<OrderItem> orderItems = new ArrayList<>();
        cartDto.getItems().forEach(cartItemDto ->
                orderItems.add(new OrderItem(productService.findById(cartItemDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found, id = " + cartItemDto.getProductId())), cartItemDto.getQuantity())));
        return orderRepository.save(new Order(user, orderItems, cartDto.getTotalPrice()));
    }

    public Order getOrder(Long id) {
        return orderRepository.getById(id);
    }

}
