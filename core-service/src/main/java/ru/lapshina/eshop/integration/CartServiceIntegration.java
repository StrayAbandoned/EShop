package ru.lapshina.eshop.integration;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.lapshina.api.CartDto;
import ru.lapshina.api.ItemNotFound;


@Component
@AllArgsConstructor
public class CartServiceIntegration {
    private final WebClient productServiceWebClient;

    public CartDto getCart() {
        return productServiceWebClient.get()
                .uri("/api/v1/cart")
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value()== HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ItemNotFound("Product not found")))
                .bodyToMono(CartDto.class)
                .block();

    }


}
