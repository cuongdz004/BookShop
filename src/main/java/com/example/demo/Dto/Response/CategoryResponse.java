package com.example.demo.Dto.Response;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CategoryResponse {
    private Long CategoryId;
    private String CategoryName;
}
