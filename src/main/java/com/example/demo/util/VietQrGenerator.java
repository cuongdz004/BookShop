package com.example.demo.util;

import com.example.demo.Config.VietQrConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class VietQrGenerator {
    @Autowired
    private VietQrConfig config;
    public String generateQR(String orderCode, BigDecimal amount){


        return "https://img.vietqr.io/image/" +
                config.getBankId() + "-" +
                config.getAccountNumber() +
                "-compact2.png?amount=" +
                amount +
                "&addInfo=" +
                orderCode;
    }

}