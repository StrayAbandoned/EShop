package ru.lapshina.eshop.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lapshina.eshop.entity.Product;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> costGreaterOrEqualsThan(BigDecimal cost) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> costLessOrEqualsThan(BigDecimal cost) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> titleLike(String title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title)));
    }

}
