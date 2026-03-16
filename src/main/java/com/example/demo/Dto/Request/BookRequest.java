package com.example.demo.Dto.Request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookRequest {
    private String name;
    private String description;
    private String author;
    private BigDecimal price;
    private String imageUrl;
    private String summary;
    private int Quantity;
    private Long categoryId;
}
