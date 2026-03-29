package com.example.demo.Dto.Request;

import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.math.BigDecimal;
import java.util.List;
@Data
public class VietQrRequest {
    private String email;
    private BigDecimal total;
    private String address;
    private String phone;
    private List<OrderRequest> items;
}
