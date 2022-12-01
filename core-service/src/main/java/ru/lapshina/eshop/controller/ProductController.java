package ru.lapshina.eshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.api.ItemNotFound;
import ru.lapshina.api.PageDto;
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
@Tag(name = "products", description = "methods for products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @Operation(summary = "get filtered list", responses = {
            @ApiResponse(description = "success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PageDto.class)))
    })
    @GetMapping
    public PageDto<ProductDto> findAllProducts(@RequestParam(required = false) Long minCost, @RequestParam(required = false) Long maxCost, @RequestParam(required = false) String title, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        Specification<Product> specification = productService.createSpec(minCost, maxCost, title);
        Page<ProductDto> myPage = productService.findAll(specification, page - 1).map(productConverter::entityToDto);
        PageDto<ProductDto> out = new PageDto<>();
        out.setItems(myPage.getContent());
        out.setPage(myPage.getNumber());
        out.setTotalPages(myPage.getTotalPages());
        return out;

    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable @Parameter(description = "product id", required = true) Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ItemNotFound("Product not found"));
        return productConverter.entityToDto(p);
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
