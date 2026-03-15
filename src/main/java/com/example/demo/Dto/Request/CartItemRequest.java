package com.example.demo.Dto.Request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartItemRequest {

    private String userEmail;
    private Long bookId;
    private Integer quantity;
    private BigDecimal price;
}
