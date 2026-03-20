package com.example.demo.Service;

import com.example.demo.Dto.Request.BookRequest;
import com.example.demo.Dto.Request.CategoryRequest;
import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Dto.Response.ReviewReponse;

import java.util.List;

public interface AdminService {
    Long getSumBooks();
    void CreateBooks(BookRequest bookRequest);
    List<ReviewReponse> getAll();
    List<CategoryResponse> getAllCategory();
    void CreateCategory(CategoryRequest categoryRequest);
    List<BookResponse> getAllBooks();
}
