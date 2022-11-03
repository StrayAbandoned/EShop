package ru.lapshina.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapshina.eshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
