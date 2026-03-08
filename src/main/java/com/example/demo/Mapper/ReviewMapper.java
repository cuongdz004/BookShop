package com.example.demo.Mapper;

import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Dto.Response.ReviewReponse;
import com.example.demo.Entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {

    public List<ReviewReponse> ReviewEntityMapperToResponse(List<Review> Reviews){
                  return Reviews.stream()
                          .map(r -> {
                              ReviewReponse reviewReponse = new ReviewReponse();
                              reviewReponse.setId(r.getId());
                              reviewReponse.setComment(r.getComment());
                              reviewReponse.setRating(r.getRating());
                              reviewReponse.setUsername(r.getUsername());
                              reviewReponse.setBookId(r.getBookId());
                              reviewReponse.setCreatedAt(r.getCreatedAt());
                              return reviewReponse;
                          })
                          .toList();
    }
}
