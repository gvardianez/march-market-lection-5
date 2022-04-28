package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.converters.OrderConverter;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.User;
import ru.geekbrains.march.market.core.exceptions.CartIsEmptyException;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.services.OrderService;
import ru.geekbrains.march.market.core.services.ProductService;
import ru.geekbrains.march.market.core.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final CartServiceIntegration cartServiceIntegration;
    private final OrderService orderService;
    private final UserService userService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createNewOrder(Principal principal) {
        log.info("Created");
        CartDto cartDto = cartServiceIntegration.getCart();
        if (cartDto.getItems().size() == 0) throw new CartIsEmptyException("Cart is empty");
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        Order order = orderService.createNewOrder(user, cartDto);
        cartServiceIntegration.clearCart();
        return orderConverter.entityToDto(order);
    }
}
