package com.example.demo.Service;

import com.example.demo.Config.MomoConfig;
import com.example.demo.Dto.Request.MomoRequest;
import com.example.demo.Dto.Response.MomoResponse;
import com.example.demo.util.HmacUtil;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class MomoService {

    private final MomoConfig momoConfig;

    public MomoService(MomoConfig momoConfig) {
        this.momoConfig = momoConfig;
    }

    public MomoResponse createPayment(MomoRequest request) throws Exception {
        String requestId = request.getOrderId(); // thường requestId = orderId cho idempotency
        String orderId = request.getOrderId();
        String orderInfo = "Thanhtoandonhang";
        String requestType = "captureWallet";
        String extraData = ""; // hoặc Base64 nếu có data
        String amount = String.valueOf(request.getAmount());
        String lang = "vi";

        // Raw signature - PHẢI sort key alphabet a-z
        String rawHash =
                "accessKey=" + momoConfig.accessKey +
                        "&amount=" + amount +
                        "&extraData=" + extraData +
                        "&ipnUrl=" + momoConfig.ipnUrl +
                        "&orderId=" + orderId +
                        "&orderInfo=" + orderInfo +
                        "&partnerCode=" + momoConfig.partnerCode +
                        "&redirectUrl=" + momoConfig.redirectUrl +
                        "&requestId=" + requestId +
                        "&requestType=" + requestType;

        // Nếu gửi lang trong body, thêm vào rawHash (an toàn hơn, dù optional)
        // rawHash += "&lang=" + lang;

        System.out.println("RAW HASH (sorted): " + rawHash);

        String signature = HmacUtil.hmacSHA256(rawHash, momoConfig.secretKey);
        System.out.println("SIGNATURE: " + signature);

        JSONObject body = new JSONObject();
        body.put("partnerCode", momoConfig.partnerCode);
        body.put("partnerName", "YourStoreName"); // optional nhưng nên có
        body.put("storeId", "YourStoreId");       // optional
        body.put("accessKey", momoConfig.accessKey);
        body.put("requestId", requestId);
        body.put("amount", amount);
        body.put("orderId", orderId);
        body.put("orderInfo", orderInfo);
        body.put("redirectUrl", momoConfig.redirectUrl);
        body.put("ipnUrl", momoConfig.ipnUrl);
        body.put("requestType", requestType);
        body.put("extraData", extraData);
        body.put("lang", lang);
        body.put("signature", signature);

        // Thêm nếu cần autoCapture (optional)
        // body.put("autoCapture", true);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                momoConfig.endpoint,
                HttpMethod.POST,
                entity,
                String.class
        );

        JSONObject json = new JSONObject(response.getBody());
        System.out.println("MoMo response: " + json.toString());

        if (json.has("resultCode") && json.getInt("resultCode") != 0) {
            throw new RuntimeException("MoMo error: " + json.getString("message") + " - Full: " + json.toString());
        }

        if (!json.has("payUrl")) {
            throw new RuntimeException("MoMo error: No payUrl - " + json.toString());
        }

        MomoResponse res = new MomoResponse();
        res.setPayUrl(json.getString("payUrl"));
        if (json.has("qrCodeUrl")) {
            res.setQrCodeUrl(json.getString("qrCodeUrl"));
        }
        // Có thể thêm deeplink, deeplinkMiniApp nếu cần

        return res;
    }
}