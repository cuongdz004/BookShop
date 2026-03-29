package com.example.demo.Service;

import com.example.demo.Dto.Request.SepayWebhookRequest;
import com.example.demo.Dto.Request.VietQrRequest;
import com.example.demo.Dto.Response.VietQrResponse;

public interface  VietQrService {
    VietQrResponse createPayment(VietQrRequest request);
    void handleSepayWebhook(SepayWebhookRequest request);

}
