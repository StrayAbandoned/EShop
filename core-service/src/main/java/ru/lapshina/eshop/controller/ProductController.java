package ru.lapshina.eshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
    public List<ProductDto> findAllProducts(@RequestParam(required = false) Long minCost, @RequestParam(required = false) Long maxCost,@RequestParam(required = false) String title, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page =1;
        }
        Specification<Product> specification = productService.createSpec(minCost,maxCost,title);


        return productService.findAll(specification, page-1).map(productConverter::entityToDto).getContent();
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
