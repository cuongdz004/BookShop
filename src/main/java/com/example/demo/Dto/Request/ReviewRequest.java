package com.example.demo.Dto.Request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewRequest {
    private String username;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;
    private Long bookId;
}

