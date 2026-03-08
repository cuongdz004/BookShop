package com.example.demo.Dto.Response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewReponse {
    private Long id;
    private String username;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;
    private Long bookId;
}
