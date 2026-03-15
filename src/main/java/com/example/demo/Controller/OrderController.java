package com.example.demo.Controller;

import com.example.demo.Dto.Request.CheckOutRequest;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout (@RequestBody CheckOutRequest checkOutRequest) {
        orderService.checkOut(checkOutRequest);
        System.out.println("Data json"+checkOutRequest);
        return ResponseEntity.ok("Order create successfully");
    }
}
