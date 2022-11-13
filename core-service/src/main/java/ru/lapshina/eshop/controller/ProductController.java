package ru.lapshina.eshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.api.ItemNotFound;
import ru.lapshina.api.ProductDto;
import ru.lapshina.eshop.converter.ProductConverter;
import ru.lapshina.eshop.entity.Product;
import ru.lapshina.eshop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts(@RequestParam(required = false) Long minCost, @RequestParam(required = false) Long maxCost) {
//        if (minCost == null && maxCost == null) {
//            return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
//
//        }
        if (minCost!=null && maxCost == null) {
            System.out.println(1);
            return productService.findProductByMin(BigDecimal.valueOf(minCost)).stream().map(productConverter::entityToDto).collect(Collectors.toList());

        }
        if (minCost != null && maxCost != null) {
            System.out.println(3);
            return productService.findBetween(BigDecimal.valueOf(minCost), BigDecimal.valueOf(maxCost)).stream().map(productConverter::entityToDto).collect(Collectors.toList());
        }
        if (minCost == null && maxCost != null) {
            System.out.println(2);
            return productService.findProductByMax(BigDecimal.valueOf(maxCost)).stream().map(productConverter::entityToDto).collect(Collectors.toList());
        }

        return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ItemNotFound("Product not found"));
        return productConverter.entityToDto(p);
    }



    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
