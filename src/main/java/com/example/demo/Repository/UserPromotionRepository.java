package com.example.demo.Repository;


import com.example.demo.Entity.UserPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPromotionRepository extends JpaRepository<UserPromotion,Long> {
    boolean existsByUserEmailAndPromotionId(String userEmail, Long promotionId);
}
