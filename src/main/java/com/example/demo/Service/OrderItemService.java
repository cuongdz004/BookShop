package com.example.demo.Service;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Entity.Book;

import java.util.List;

public interface OrderItemService {
    List<BookResponse> getTopSellingBooks();
}
