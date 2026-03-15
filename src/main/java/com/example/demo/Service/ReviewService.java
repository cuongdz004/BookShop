package com.example.demo.Service;

import com.example.demo.Dto.Response.ReviewReponse;

import java.util.List;

public interface ReviewService {
    List<ReviewReponse> getReviewByBookId(Long Id);
    List<Object[]> getRating(Long Id);
}
