package ru.lapshina.eshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapshina.eshop.entity.Product;
import ru.lapshina.eshop.repository.ProductRepository;
import ru.lapshina.eshop.soap.Productxml;

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

    public List<Product> findAll() {
        return repository.findAll();
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
}
