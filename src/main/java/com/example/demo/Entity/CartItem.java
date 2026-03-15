package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_item")
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "useremail", nullable = false)
    private String userEmail;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}