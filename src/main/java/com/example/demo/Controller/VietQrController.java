package com.example.demo.Controller;

import com.example.demo.Dto.Request.VietQrRequest;
import com.example.demo.Dto.Response.VietQrResponse;
import com.example.demo.Entity.Order;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.VietQrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class VietQrController {

    @Autowired
    private VietQrService vietQrService;
    @Autowired
    private OrderRepository orderRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody VietQrRequest request){

        VietQrResponse response = vietQrService.createPayment(request);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/status/{orderCode}")
    public ResponseEntity<?> checkStatus(@PathVariable String orderCode){

        Optional<Order> optional = orderRepository.findByOrderCode(orderCode);

        if(optional.isEmpty()){
            return ResponseEntity.ok("NOT_FOUND");
        }

        return ResponseEntity.ok(optional.get().getStatus());
    }
}