package ru.geekbrains.march.market.cart.utils;

import lombok.Data;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.exceptions.FieldValidationException;
import ru.geekbrains.march.market.cart.exceptions.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public void add(ProductDto p) {
        for (CartItem item : items) {
            if (item.getProductId().equals(p.getId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        CartItem cartItem = new CartItem(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice());
        items.add(cartItem);
        recalculate();
    }

    public void changeProductQuantity(Long id, Integer delta) {
        for (CartItem item : items) {
            if (item.getProductId().equals(id)) {
                item.changeQuantity(delta);
                if (item.getQuantity() <= 0) items.remove(item);
                recalculate();
                return;
            }
        }
        throw new ResourceNotFoundException("Product not found, id = " + id);
    }

    public void setProductQuantity(Long id, Integer newQuantity) {
        if (newQuantity < 0) throw new FieldValidationException("New quantity must be at least zero");
        if (newQuantity == 0) {
            remove(id);
            return;
        }
        for (CartItem item : items) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(newQuantity);
                recalculate();
                return;
            }
        }
        throw new ResourceNotFoundException("Product not found, id = " + id);
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public void remove(Long productId) {
        if (!items.removeIf(cartItem -> cartItem.getProductId().equals(productId)))
            throw new ResourceNotFoundException("Product not found, id = " + productId);
        ;
        recalculate();
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }
}
