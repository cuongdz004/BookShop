package com.example.demo.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SepayWebhookRequest {
//    private String transaction_id;
    @JsonProperty("transferAmount")
    private BigDecimal amount;

    private String content;

//    private String account_number;
}
