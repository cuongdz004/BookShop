package com.example.demo.Controller;


import com.example.demo.Dto.Request.BookRequest;
import com.example.demo.Dto.Request.CategoryRequest;
import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Dto.Response.ReviewReponse;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.BookService;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderRepository  orderService;
    @GetMapping("")
    public ResponseEntity<List<BookResponse>> getAllbook() {
        return ResponseEntity.ok(adminService.getAllBooks());
    }

    @GetMapping("/sumBooks")
    public ResponseEntity<Long> getSumBooks(){
        return ResponseEntity.ok(adminService.getSumBooks());
    }

    @PutMapping("/UpdateBooks/{bookId}")
    public ResponseEntity<?> UpdateBooks(@PathVariable Long bookId){
        return null;
    }
    @PostMapping("/CreateBook")
    public ResponseEntity<?> CreateBooks(@RequestBody BookRequest bookRequest){
        adminService.CreateBooks(bookRequest);
        return ResponseEntity.ok("Thanh cong");
    }

    @GetMapping("/AllCategory")
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
        return ResponseEntity.ok(adminService.getAllCategory());
    }

    @PostMapping("/CreateCategory")
    public ResponseEntity<?> CreateCategory(@RequestBody CategoryRequest categoryRequest){
        adminService.CreateCategory(categoryRequest);
        return ResponseEntity.ok("Thanh cong");
    }
    @GetMapping("/AllReviews")
    public ResponseEntity<List<ReviewReponse>> getAll(){
        return ResponseEntity.ok(adminService.getAll());
    }

    @GetMapping("/ALLOrder")
    public ResponseEntity<List<Order>> getallorder() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
