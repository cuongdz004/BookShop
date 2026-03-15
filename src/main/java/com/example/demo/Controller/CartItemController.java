package com.example.demo.Controller;


import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.CartItemResponse;
import com.example.demo.Entity.CartItem;
import com.example.demo.Repository.CartItemRepository;
import com.example.demo.Service.CartItemService;
import com.example.demo.Service.impl.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/CartItem")
@RestController
public class CartItemController {
    @Autowired
    private CartItemService cartService;
    @GetMapping("/CartSave/{useremail}")
    public ResponseEntity<List<CartItemResponse>> getBookDetail(@PathVariable("useremail") String useremail){
        return ResponseEntity.ok(cartService.getAllcartByUser(useremail));
    }

    @PostMapping("/createcart")
    public ResponseEntity<?> createCart(@RequestBody CartItem cartItem) {
//        System.out.println("===== CART DATA FROM FE =====");
//        System.out.println("userEmail: " + cartItem.getUserEmail());
//        System.out.println("bookId: " + cartItem.getBookId());
//        System.out.println("quantity: " + cartItem.getQuantity());
//        System.out.println("price: " + cartItem.getPrice());
//        System.out.println("=============================");
////        CartItem savedCart = cartItemRepository.save(cartItem);
        cartService.saveCartItem(cartItem);
//        return ResponseEntity.ok(savedCart);
        return ResponseEntity.ok("Added to cart");

    }

    @DeleteMapping("/DeleteCartItem/{cartItemId}")
    public ResponseEntity<?> deleteCart(@PathVariable("cartItemId") Long CartItemId){
        cartService.deleteCartItem(CartItemId);
        return ResponseEntity.ok( "thanh cong");
    }
}
