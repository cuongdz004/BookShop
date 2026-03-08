package com.example.demo.Service;

import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Repository.CategoryRepository;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();
}
