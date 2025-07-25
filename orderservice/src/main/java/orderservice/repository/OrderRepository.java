package orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
