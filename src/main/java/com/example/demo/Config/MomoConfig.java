package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MomoConfig {

    @Value("${momo.partnerCode}")
    public String partnerCode;

    @Value("${momo.accessKey}")
    public String accessKey;

    @Value("${momo.secretKey}")
    public String secretKey;

    @Value("${momo.endpoint}")
    public String endpoint;

    @Value("${momo.redirectUrl}")
    public String redirectUrl;

    @Value("${momo.ipnUrl}")
    public String ipnUrl;
}