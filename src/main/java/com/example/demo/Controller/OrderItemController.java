package com.example.demo.Controller;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class OrderItemController {
    @Autowired
    private OrderService orderItemService;
    @GetMapping("/orders/toporder")
    public ResponseEntity<List<BookResponse>> getTopOrder() {
        return ResponseEntity.ok(orderItemService.getTopSellingBooks());
    }

}
