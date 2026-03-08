package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "author", length = 255)
    private String author;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;


    @Column(name = "summary")
    private String summary;

    @Column(name = "category_id")
    private Long categoryId;
}