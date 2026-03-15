package com.example.demo.Mapper;


import com.example.demo.Dto.Request.UserPromotionRequest;
import com.example.demo.Entity.Promotion;
import com.example.demo.Entity.UserPromotion;
import org.springframework.stereotype.Component;

@Component
public class UserPromotionMapper {

    public UserPromotion RequestMapperToEnity(UserPromotionRequest req){

        UserPromotion userPromotion = new UserPromotion();

        userPromotion.setUserEmail(req.getUseremail());
        userPromotion.setPromotionId(req.getPromotion());
        userPromotion.setUsed(false);

        return userPromotion;
    }
}