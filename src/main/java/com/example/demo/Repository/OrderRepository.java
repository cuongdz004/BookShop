package com.example.demo.Repository;

import com.example.demo.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEmail(String email);

    Optional<Order> findByOrderCode(String orderCode);
    @Query("""
SELECT p FROM Order p
WHERE p.status = 'PENDING'
AND p.createdAt < :time
""")
    List<Order> findExpiredOrders(LocalDateTime time);
    Optional<Order> findOrderById(Long orderId);
}
