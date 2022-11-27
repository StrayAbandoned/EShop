package ru.lapshina.eshop.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.lapshina.api.ProductDto;
import ru.lapshina.eshop.entity.Product;
import ru.lapshina.eshop.repository.ProductRepository;
import ru.lapshina.eshop.soap.Productxml;
import ru.lapshina.eshop.specification.ProductSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public static final Function<Product, Productxml> entityToSoap = p->{
        Productxml productxml = new Productxml();
        productxml.setId(p.getId());
        productxml.setTitle(p.getTitle());
        productxml.setCost(p.getCost());
        return productxml;
    };

    public Page<Product> findAll(Specification<Product> specification, int page) {
        return repository.findAll(specification, PageRequest.of(page, 5));
    }

    public List<Productxml> findAllXml() {
        return repository.findAll().stream().map(entityToSoap).collect(Collectors.toList());
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public Specification<Product> createSpec(Long min, Long max, String title){
        Specification<Product> specification = Specification.where(null);
        if(min!=null) {
            specification = specification.and(ProductSpecification.costGreaterOrEqualsThan(BigDecimal.valueOf(min)));
        }
        if(max!=null) {
            specification = specification.and(ProductSpecification.costLessOrEqualsThan(BigDecimal.valueOf(max)));
        }
        if(min!=null) {
            specification = specification.and(ProductSpecification.titleLike(title));
        }
        return specification;
    }
}
