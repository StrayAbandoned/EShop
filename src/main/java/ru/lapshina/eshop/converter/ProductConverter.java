package ru.lapshina.eshop.converter;

import org.springframework.stereotype.Component;
import ru.lapshina.eshop.dto.ProductDto;
import ru.lapshina.eshop.entity.Product;

@Component
public class ProductConverter {
    public ProductDto entityToDto (Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
    public Product DtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }
}
