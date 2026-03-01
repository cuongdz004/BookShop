package com.example.demo.Service;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();
    List<BookResponse> getTop10NewArrivals();
    List<BookResponse> getPriceLowToHigh();
    List<BookResponse> getPriceHighToLow();
    List<BookResponse> getSearhProduct(String Name);
    List<BookResponse> getPricebetween(BigDecimal min,BigDecimal max);
    List<BookResponse> getCate(String cate);

}
