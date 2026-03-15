package com.example.demo.Controller;


import com.example.demo.Dto.Request.UserPromotionRequest;
import com.example.demo.Service.UserPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UserPromotion")
public class UserPromotionController {

    @Autowired
    private UserPromotionService userPromotionService;

    @PostMapping("/PostPromotion")
    public ResponseEntity<?> PostPromotion(@RequestBody UserPromotionRequest userPromotionRequest){
          userPromotionService.PostUserPromotion(userPromotionRequest);
          return ResponseEntity.ok("Da them ma giam gia");
    }
}
