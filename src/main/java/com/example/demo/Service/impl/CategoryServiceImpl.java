package com.example.demo.Service.impl;

import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryMapper.CategoryEntityMapperToResponse(categoryRepository.findAll());
    }

    @Override
    public List<CategoryResponse> getAllCategory(String useremail) {
        return List.of();
    }
}
