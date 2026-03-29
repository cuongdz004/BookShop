package com.example.demo.Service;

import com.example.demo.Dto.Request.CheckOutRequest;
import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<BookResponse> getTopSellingBooks();
    void checkOut(CheckOutRequest checkOutRequest);
    int getSumOrder();
    List<OrderResponse> getOrderByEmail(String email);
    void confirmDelivered(Long id);
    void cancelOrder(Long orderId);
}
