package com.example.demo.Dto.Request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private Long bookId;
    private Integer quantity;
    private BigDecimal price;
}
