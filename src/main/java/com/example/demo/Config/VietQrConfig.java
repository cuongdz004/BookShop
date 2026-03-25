package com.example.demo.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "vietqr")
@Data
public class VietQrConfig {

    private String bankId;
    private String accountNumber;
    private String accountName;

}
