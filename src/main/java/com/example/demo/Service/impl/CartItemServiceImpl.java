package com.example.demo.Service.impl;

import com.example.demo.Dto.Response.CartItemResponse;
import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Entity.CartItem;
import com.example.demo.Repository.CartItemRepository;
import com.example.demo.Service.CartItemService;
import com.example.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartItemServiceImpl  implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem saveCartItem(CartItem cartItem){

        Optional<CartItem> existing = cartItemRepository
                .findByUserEmailAndBookId(cartItem.getUserEmail(), cartItem.getBookId());

        if(existing.isPresent()){

            CartItem item = existing.get();

            item.setQuantity(item.getQuantity() + cartItem.getQuantity());

            return cartItemRepository.save(item);

        }

        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItemResponse> getAllcartByUser(String useremail) {
        return cartItemRepository.findCartByEmail(useremail);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
