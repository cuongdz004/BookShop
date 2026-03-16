package com.example.demo.Controller;

import com.example.demo.Dto.Request.CategoryRequest;
import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/AllCategory")
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
            return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/CreateCategory")
    public ResponseEntity<?> CreateCategory(@RequestBody CategoryRequest categoryRequest){
             categoryService.CreateCategory(categoryRequest);
             return ResponseEntity.ok("Thanh cong");
    }
}
