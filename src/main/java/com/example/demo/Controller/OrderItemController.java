package com.example.demo.Controller;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.OrderResponse;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.OrderService;
import com.example.demo.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    @GetMapping("/orders/toporder")
    public ResponseEntity<List<BookResponse>> getTopOrder() {
        return ResponseEntity.ok(orderService.getTopSellingBooks());
    }
    @GetMapping("/orders/info/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getInfo(@PathVariable Long orderId){

        OrderResponse order = orderItemService.getInfoBookByOrderItem(orderId);

        ApiResponse<OrderResponse> response =
                new ApiResponse<>("success", order);

        return ResponseEntity.ok(response);
    }
}
