package com.example.demo.Dto.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private String author;
    private BigDecimal price;
    private String imageUrl;
    private String summary;
    private int Quantity;
}
