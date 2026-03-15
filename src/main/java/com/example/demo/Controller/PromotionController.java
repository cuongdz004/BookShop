package com.example.demo.Controller;


import com.example.demo.Entity.Promotion;
import com.example.demo.Service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/getAllPromotion")
    public ResponseEntity<List<Promotion>> getAllPromotion(){
        return ResponseEntity.ok(promotionService.getAllPromotion());
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkPromotion(
            @RequestParam String code,
            @RequestParam String email){
        System.out.println(code);
        System.out.println(email);
        Promotion promotion = promotionService.checkPromotion(code,email);

        return ResponseEntity.ok(promotion);
    }

}
