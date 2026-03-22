package com.example.demo.Controller;

import com.example.demo.Dto.Request.ReviewRequest;
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

    @GetMapping("/AllReviews")
    public ResponseEntity<List<ReviewReponse>> getAll(){
        return ResponseEntity.ok(reviewService.getAll());
    }


    @PostMapping("/CreateReview")
    public ResponseEntity<?> CreateReview(@RequestBody ReviewRequest newReviewRequest){
            reviewService.CreateReview(newReviewRequest);
         return  ResponseEntity.ok("tao review thanh cong");
    }
}
