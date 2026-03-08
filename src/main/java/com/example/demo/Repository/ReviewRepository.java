package com.example.demo.Repository;


import com.example.demo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.bookId = :BookId")
    List<Review> ListReview(Long BookId);
}
