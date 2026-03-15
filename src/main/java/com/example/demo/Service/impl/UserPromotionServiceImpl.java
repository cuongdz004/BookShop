package com.example.demo.Service.impl;


import com.example.demo.Dto.Request.UserPromotionRequest;
import com.example.demo.Mapper.UserPromotionMapper;
import com.example.demo.Repository.UserPromotionRepository;
import com.example.demo.Service.UserPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPromotionServiceImpl implements UserPromotionService {

    @Autowired
    private UserPromotionRepository userPromotionRepository;
    @Autowired
    private UserPromotionMapper userPromotionMapper;

    @Override
    public void PostUserPromotion(UserPromotionRequest userPromotionRequest) {
        boolean existed = userPromotionRepository
                .existsByUserEmailAndPromotionId(userPromotionRequest.getUseremail(), userPromotionRequest.getPromotion());

        if(existed){
            throw new RuntimeException("Bạn đã lưu mã này rồi");
        }
        userPromotionRepository.save(userPromotionMapper.RequestMapperToEnity(userPromotionRequest));
    }
}
