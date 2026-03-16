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

        String requestId = request.getOrderId();
        String orderId = request.getOrderId();
        String orderInfo = "Thanhtoandonhang";
        String requestType = "captureWallet";
//        String extraData = Base64.getEncoder().encodeToString("".getBytes());
        String extraData = "";
        String amount = String.valueOf(request.getAmount());

        // Raw signature theo đúng thứ tự MoMo yêu cầu
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

        System.out.println("RAW HASH: " + rawHash);

        String signature = HmacUtil.hmacSHA256(rawHash, momoConfig.secretKey);

        System.out.println("SIGNATURE: " + signature);

        JSONObject body = new JSONObject();

        body.put("partnerCode", momoConfig.partnerCode);

        body.put("accessKey", momoConfig.accessKey);
        body.put("requestId", requestId);
        body.put("amount", amount);
        body.put("orderId", orderId);
        body.put("orderInfo", orderInfo);

        body.put("redirectUrl", momoConfig.redirectUrl);
        body.put("ipnUrl", momoConfig.ipnUrl);

        body.put("requestType", requestType);
        body.put("extraData", extraData);

        body.put("lang", "vi");
        body.put("signature", signature);

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

        if (!json.has("payUrl")) {
            throw new RuntimeException("MoMo error: " + json.toString());
        }

        MomoResponse res = new MomoResponse();

        res.setPayUrl(json.getString("payUrl"));

        if (json.has("qrCodeUrl")) {
            res.setQrCodeUrl(json.getString("qrCodeUrl"));
        }

        return res;
    }
}