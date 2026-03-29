package com.example.demo.Service;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.OrderResponse;

import java.util.List;

public interface OrderItemService {
    OrderResponse getInfoBookByOrderItem(Long orderId);
}
