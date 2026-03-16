package com.example.demo.Controller;

import com.example.demo.Dto.Request.MomoRequest;
import com.example.demo.Dto.Response.MomoResponse;
import com.example.demo.Service.MomoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/momo")
public class MomoController {

    private final MomoService momoService;

    public MomoController(MomoService momoService) {
        this.momoService = momoService;
    }

    @PostMapping("/create")
    public MomoResponse createPayment(@RequestBody MomoRequest request) throws Exception {

        return momoService.createPayment(request);
    }

    @PostMapping("/callback")
    public void callback(@RequestBody String body){

        System.out.println("MoMo callback: " + body);
    }
}
