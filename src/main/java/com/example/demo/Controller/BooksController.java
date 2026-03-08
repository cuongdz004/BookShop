package com.example.demo.Controller;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RequestMapping("/Book")
@RestController
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("/BookDetail/{id}")
    public ResponseEntity<BookResponse> getBookDetail(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookByBookId(id));
    }

    @GetMapping("/Top10NewArrivals")
    public ResponseEntity<List<BookResponse>> getTop10NewArrivals() {

        return ResponseEntity.ok(bookService.getTop10NewArrivals());
    }

    @GetMapping("")
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/PriceLowToHigh")
    public ResponseEntity<List<BookResponse>> getPriceLowToHigh() {
        return ResponseEntity.ok(bookService.getPriceLowToHigh());
    }

    @GetMapping("/PriceHighToLow")
    public ResponseEntity<List<BookResponse>> getPriceHighToLowJson() {
        return ResponseEntity.ok(bookService.getPriceHighToLow());
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> search(
            @RequestParam("keyword") String keyword
    ) {
        return ResponseEntity.ok(bookService.getSearhProduct(keyword));
    }

    @GetMapping("/price-between")
    public ResponseEntity<List<BookResponse>> priceBetween(
            @RequestParam("min") BigDecimal min,
            @RequestParam("max") BigDecimal max
    ) {
        return ResponseEntity.ok(bookService.getPricebetween(min, max));
    }

    @GetMapping("/filtercate")
    public ResponseEntity<List<BookResponse>> filtercate(
            @RequestParam("CategoryName") String CategoryName
    ) {
        return ResponseEntity.ok(bookService.getByCate(CategoryName));
    }
}