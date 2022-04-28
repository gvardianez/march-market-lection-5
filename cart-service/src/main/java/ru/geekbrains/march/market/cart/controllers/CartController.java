package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @GetMapping("/change_quantity")
    public void changeProductQuantityInCart(@RequestParam Long id, @RequestParam Integer delta) {
        cartService.changeProductQuantity(id, delta);
    }
    @GetMapping("/set_quantity")
    public void setNewQuantity(@RequestParam Long id, @RequestParam Integer newQuantity) {
        cartService.setProductQuantity(id, newQuantity);
    }

    @GetMapping("/remove/{id}")
    public void removeProductFromCartById(@PathVariable Long id) {
        cartService.removeProductById(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

}
