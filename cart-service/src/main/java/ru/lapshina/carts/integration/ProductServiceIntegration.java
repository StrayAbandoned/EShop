package ru.lapshina.carts.integration;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.lapshina.api.ItemNotFound;
import ru.lapshina.api.ProductDto;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductByID(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/"+id)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value()== HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ItemNotFound("Product not found")))
                .bodyToMono(ProductDto.class)
                .block();

    }
}
