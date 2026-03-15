package com.example.demo.Repository;

import com.example.demo.Entity.CartItem;
import com.example.demo.Entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findByCode(String code);
}
