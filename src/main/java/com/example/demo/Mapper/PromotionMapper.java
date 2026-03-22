package com.example.demo.Mapper;

import com.example.demo.Dto.Request.PromotionRequest;
import com.example.demo.Dto.Response.PromotionResponse;
import com.example.demo.Entity.Promotion;
import com.example.demo.Repository.PromotionRepository;

import java.util.List;

public class PromotionMapper {

    public Promotion PromotionRequestMapperToEntity(Promotion promotion) {
        Promotion newPromontion = new Promotion();
        newPromontion.setCode(promotion.getCode());
        newPromontion.setDescription(promotion.getDescription());
        newPromontion.setDiscountPercent(promotion.getDiscountPercent());
        newPromontion.setMinOrder(promotion.getMinOrder());
        return newPromontion;
    }

    public List<PromotionResponse> PromotionMapperToResponse(List<Promotion> listpromotion) {
        return listpromotion.stream()
                .map(p -> {
                    PromotionResponse promotionResponse = new PromotionResponse();
                    promotionResponse.setId(p.getId());
                    promotionResponse.setCode(p.getCode());
                    promotionResponse.setDescription(p.getDescription());
                    promotionResponse.setMinOrder(p.getMinOrder());
                    promotionResponse.setExpiredDate(p.getExpiredDate());
                    return promotionResponse;
                })
                .toList();
    }
}
