package com.example.demo.Service.impl;

import com.example.demo.Entity.Promotion;
import com.example.demo.Repository.PromotionRepository;
import com.example.demo.Repository.UserPromotionRepository;
import com.example.demo.Service.PromotionService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private UserPromotionRepository userPromotionRepository;

    @Override
    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion checkPromotion(String code, String userEmail){

        Promotion promotion = promotionRepository
                .findByCode(code)
                .orElseThrow(() -> new RuntimeException("Mã không tồn tại"));

        boolean saved = userPromotionRepository
                .existsByUserEmailAndPromotionId(userEmail, promotion.getId());

        if(!saved){
            throw new RuntimeException("Bạn chưa lưu mã này");
        }

        return promotion;
    }
}
