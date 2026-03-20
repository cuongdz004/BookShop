package com.example.demo.Dto.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    public Long id;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private String phone;
    private String email;
}