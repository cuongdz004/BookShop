package com.example.demo.Dto.Request;


import lombok.Data;

@Data
public class PromotionRequest {
    private String code;

    private Integer discountPercent;

    private Integer minOrder;

    private String description;

}
