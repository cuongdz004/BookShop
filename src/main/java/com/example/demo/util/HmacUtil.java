package com.example.demo.util;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HmacUtil {

    public static String hmacSHA256(String data, String key) throws Exception {

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        mac.init(secretKey);

        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));

        StringBuilder hex = new StringBuilder(2 * rawHmac.length);
        for (byte b : rawHmac) {
            String hexStr = Integer.toHexString(0xff & b);
            if (hexStr.length() == 1) {
                hex.append('0');
            }
            hex.append(hexStr);
        }

        return hex.toString();
    }
}