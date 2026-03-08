package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CookieValue;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private BigDecimal price;
}
