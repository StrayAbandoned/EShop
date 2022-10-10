package ru.lapshina.eshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.eshop.dto.ProductDto;
import ru.lapshina.eshop.entity.Product;
import ru.lapshina.eshop.exception.AppException;
import ru.lapshina.eshop.exception.ItemNotFound;
import ru.lapshina.eshop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getCost())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ItemNotFound("Product not found"));
        return new ProductDto(p.getId(), p.getTitle(), p.getCost());
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
