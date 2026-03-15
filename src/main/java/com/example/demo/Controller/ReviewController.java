package com.example.demo.Controller;

import com.example.demo.Dto.Response.ReviewReponse;
import com.example.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/AllReviewByBookId/{id}")
    public ResponseEntity<List<ReviewReponse>> getAllReviewByBookId(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getReviewByBookId(id));
    }

    @GetMapping("/RatePoint/{id}")
    public ResponseEntity<List<Object[]>> getRatePoint(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getRating(id));
    }
}
