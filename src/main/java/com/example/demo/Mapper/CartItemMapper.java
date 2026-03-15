package com.example.demo.Mapper;


import com.example.demo.Dto.Request.CartItemRequest;
import com.example.demo.Dto.Response.CartItemResponse;
import com.example.demo.Entity.CartItem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CartItemMapper {

    public CartItem RequestMappingToEntity(CartItemRequest cartItemRequest) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setUserEmail(cartItemRequest.getUserEmail());
        cartItem.setBookId(cartItemRequest.getBookId());
        cartItem.setPrice(cartItemRequest.getPrice());
        cartItem.setCreatedAt(LocalDateTime.now());
        return cartItem;
    }


//    public List<CartItemResponse> EnityMappingToResponse(CartItem cartItem) {
//
//        return  null;
//    }


}
