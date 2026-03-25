package com.example.demo.Dto.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VietQrResponse {
    private String orderCode;
    private String qrUrl;
    private BigDecimal amount;
}

