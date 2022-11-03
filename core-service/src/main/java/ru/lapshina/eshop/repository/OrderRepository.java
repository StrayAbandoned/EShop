package ru.lapshina.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapshina.eshop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
