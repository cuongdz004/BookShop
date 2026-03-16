package com.example.demo.Service;

import com.example.demo.Dto.Request.BookRequest;

public interface AdminService {
    Long getSumBooks();
    void CreateBooks(BookRequest bookRequest);

}
