package ru.geekbrains.march.market.api;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Long id;
    private String customer;
    private List<OrderItemDto> items;
    private BigDecimal price;

    public OrderDto(Long id, String customer, List<OrderItemDto> items, BigDecimal price) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", price=" + price +
                '}';
    }
}
