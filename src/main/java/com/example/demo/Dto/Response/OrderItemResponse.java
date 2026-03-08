package com.example.demo.Dto.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private Long id;
    private Long orderId;
    private Long bookId;
    private Long quantity;
    private BigDecimal price;
}
