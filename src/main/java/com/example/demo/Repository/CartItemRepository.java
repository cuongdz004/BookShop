package com.example.demo.Repository;


import com.example.demo.Dto.Response.CartItemResponse;
import com.example.demo.Entity.CartItem;
import com.example.demo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserEmail(String userEmail);
    Optional<CartItem> findByUserEmailAndBookId(String userEmail, Long bookId);
    @Query("""
SELECT new com.example.demo.Dto.Response.CartItemResponse(
    c.id,
    c.userEmail,
    c.bookId,
    c.quantity,
    b.name,
    c.createdAt,
    c.price,
    b.imageUrl
)
FROM CartItem c, Book b
WHERE c.bookId = b.id
AND c.userEmail = :email
""")
    List<CartItemResponse> findCartByEmail(@Param("email") String email);
}
