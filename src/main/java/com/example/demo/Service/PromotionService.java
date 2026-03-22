package com.example.demo.Service;

import com.example.demo.Dto.Response.PromotionResponse;
import com.example.demo.Entity.Promotion;

import java.util.List;

public interface PromotionService {
    List<PromotionResponse> getAllPromotion();
    Promotion checkPromotion(String code, String userEmail);
}
