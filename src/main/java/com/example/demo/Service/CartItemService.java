package com.example.demo.Service;

import com.example.demo.Dto.Response.CartItemResponse;
import com.example.demo.Entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem saveCartItem(CartItem cartItem);
    List<CartItemResponse> getAllcartByUser(String useremail);
    void deleteCartItem(Long id);
}
