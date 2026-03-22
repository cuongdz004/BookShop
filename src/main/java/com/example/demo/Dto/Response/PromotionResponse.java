package com.example.demo.Dto.Response;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PromotionResponse {
    private Long id;

    private String code;

    private Integer discountPercent;

    private Integer minOrder;

    private String description;

    private LocalDateTime expiredDate;
}
