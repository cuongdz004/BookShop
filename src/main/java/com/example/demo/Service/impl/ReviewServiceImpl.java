package com.example.demo.Service.impl;

import com.example.demo.Dto.Response.ReviewReponse;
import com.example.demo.Mapper.ReviewMapper;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewReponse> getReviewByBookId(Long Id) {
        return reviewMapper.ReviewEntityMapperToResponse(reviewRepository.ListReview(Id));
    }

    @Override
    public List<Object[]> getRating(Long Id) {
        return reviewRepository.countRatingByBook(Id);
    }

    @Override
    public List<ReviewReponse> getAll() {
        return reviewMapper.ReviewEntityMapperToResponse(reviewRepository.findAll());
    }
}
