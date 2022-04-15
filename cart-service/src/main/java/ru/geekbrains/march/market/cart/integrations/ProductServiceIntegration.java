package ru.geekbrains.march.market.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.march.market.api.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public ProductDto findById(Long id) {
        return restTemplate.getForObject("http://localhost:8189/market-core/api/v1/products/" + id, ProductDto.class);
    }
}
