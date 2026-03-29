package com.example.demo.Dto.Response;

import com.example.demo.Entity.Book;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    public Long id;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private String phone;
    private String email;
    private String address;
    private String shippingStatus;
    private List<BookResponse> books;
}