package com.example.demo.Service;

import com.example.demo.Entity.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAllPromotion();
    Promotion checkPromotion(String code, String userEmail);
}
