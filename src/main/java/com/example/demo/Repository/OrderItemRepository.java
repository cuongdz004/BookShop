package com.example.demo.Repository;

import com.example.demo.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("""
        SELECT oi.bookId
        FROM OrderItem oi
        GROUP BY oi.bookId
        ORDER BY SUM(oi.quantity) DESC
    """)
    List<Long> findTopSellingBookIds(Pageable pageable);
}
