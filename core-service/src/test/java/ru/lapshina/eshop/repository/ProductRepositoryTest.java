package ru.lapshina.eshop.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lapshina.eshop.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findProductsByCostLessThanEqualTest(){
        BigDecimal cost = BigDecimal.valueOf(100);
        Product p1 = new Product();
        Product p2 = new Product();
        p1.setTitle("Popcorn");
        p1.setCost(BigDecimal.valueOf(300));
        p2.setTitle("Meat");
        p2.setCost(BigDecimal.valueOf(100));
        entityManager.persist(p1);
        entityManager.persist(p2);
        List<Product> products = productRepository.findProductsByCostLessThanEqual(cost);
        Assertions.assertEquals(3, products.size());
    }

}
