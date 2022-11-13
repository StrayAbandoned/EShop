package ru.lapshina.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lapshina.eshop.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCostGreaterThanEqual(BigDecimal min);

    List<Product> findProductsByCostLessThanEqual(BigDecimal max);
    List<Product> findProductsByCostGreaterThanEqualAndCostLessThanEqual(BigDecimal min, BigDecimal max);
}
