package ru.lapshina.carts.integration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lapshina.api.ProductDto;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductByID(Long id) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8082/market/api/v1/products/" + id, ProductDto.class));
    }
}
