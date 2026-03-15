package com.example.demo.Dto.Request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CheckOutRequest {
    private String email;
    private BigDecimal total;
    private List<OrderRequest> items;
}
