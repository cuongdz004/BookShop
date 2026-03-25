package com.example.demo.webhook;

import com.example.demo.Dto.Request.SepayWebhookRequest;
import com.example.demo.Service.VietQrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sepay")
public class SepayWebhookController {

    @Autowired
    private VietQrService vietQrService;

    @PostMapping("/webhook")
    public ResponseEntity<?> receiveWebhook(@RequestBody SepayWebhookRequest request){
        System.out.println("Webhook data: " + request);
        vietQrService.handleSepayWebhook(request);

        return ResponseEntity.ok("received");
    }
}