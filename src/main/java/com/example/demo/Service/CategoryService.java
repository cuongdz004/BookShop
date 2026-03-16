package com.example.demo.Service;

import com.example.demo.Dto.Request.CategoryRequest;
import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Entity.Category;
import com.example.demo.Repository.CategoryRepository;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();
    List<CategoryResponse> getAllCategory(String useremail);
    void CreateCategory(CategoryRequest categoryRequest);
}
