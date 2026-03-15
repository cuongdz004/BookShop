package com.example.demo.Dto.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartItemResponse {

    private Long id;
    private String useremail;
    private Long bookId;
    private Integer quantity;
    private String name;
    private LocalDateTime createdAt;
    private BigDecimal price;
    private String imageUrl;

    public CartItemResponse(Long id, String useremail, Long bookId,
                            Integer quantity,String name, LocalDateTime createdAt,
                            BigDecimal price, String imageUrl) {

        this.id = id;
        this.useremail = useremail;
        this.bookId = bookId;
        this.quantity = quantity;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
