package com.example.demo.Service;

import com.example.demo.Dto.Request.CheckOutRequest;
import com.example.demo.Dto.Response.BookResponse;

import java.util.List;

public interface OrderService {
    List<BookResponse> getTopSellingBooks();
    void checkOut(CheckOutRequest checkOutRequest);
}
