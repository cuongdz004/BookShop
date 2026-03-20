package com.example.demo.Service.impl;

import com.example.demo.Dto.Request.BookRequest;
import com.example.demo.Dto.Request.CategoryRequest;
import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Dto.Response.ReviewReponse;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Mapper.ReviewMapper;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;



    @Override
    public Long getSumBooks() {
        return bookRepository.count();
    }

    @Override
    public void CreateBooks(BookRequest bookRequest) {
        bookRepository.save(bookMapper.RequestToEntity(bookRequest));
    }

    @Override
    public List<ReviewReponse> getAll() {
        return reviewMapper.ReviewEntityMapperToResponse(reviewRepository.findAll());
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryMapper.CategoryEntityMapperToResponse(categoryRepository.findAll());
    }

    @Override
    public void CreateCategory(CategoryRequest categoryRequest) {
        categoryRepository.save(categoryMapper.CategoryRequestMapperToEntity(categoryRequest));
    }
    @Override
    public List<BookResponse> getAllBooks() {
        return bookMapper.EntityMapperToResponse(bookRepository.findAll());
    }
}
