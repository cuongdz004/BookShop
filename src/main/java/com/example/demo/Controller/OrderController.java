package com.example.demo.Controller;

import com.example.demo.Dto.Request.CheckOutRequest;
import com.example.demo.Dto.Response.OrderResponse;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getorder/{email}")
    public ResponseEntity<List<OrderResponse>> getOrder(@PathVariable String email) {

        System.out.println("Email: " + email);

        List<OrderResponse> orders = orderService.getOrderByEmail(email);

        return ResponseEntity.ok(orders);
    }
}
