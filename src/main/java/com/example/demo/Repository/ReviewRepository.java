package com.example.demo.Repository;


import com.example.demo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.bookId = :BookId")
    List<Review> ListReview(Long BookId);

    @Query("""
SELECT r.rating, COUNT(r)
FROM Review r
WHERE r.bookId = :bookId
GROUP BY r.rating
""")
    List<Object[]> countRatingByBook(@Param("bookId") Long bookId);
}
