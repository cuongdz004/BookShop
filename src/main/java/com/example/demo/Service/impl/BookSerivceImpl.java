package com.example.demo.Service.impl;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookSerivceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;


    @Override
    public List<BookResponse> getAllBooks() {
        return bookMapper.EntityMapperToResponse(bookRepository.findAll());
    }

    @Override
    public List<BookResponse> getTop10NewArrivals() {
        Pageable pageable = PageRequest.of(0, 10);
        return bookMapper.EntityMapperToResponse(bookRepository.getTop10NewArrivals(pageable));
    }

    @Override
    public List<BookResponse> getPriceLowToHigh() {
        return bookMapper.EntityMapperToResponse(bookRepository.getPriceLowToHigh());
    }

    @Override
    public List<BookResponse> getPriceHighToLow() {
        return bookMapper.EntityMapperToResponse(bookRepository.getPriceHighToLow());
    }

    @Override
    public List<BookResponse> getSearhProduct(String Name) {
        return  bookMapper.EntityMapperToResponse(bookRepository.searchBooks(Name));
    }

    @Override
    public List<BookResponse> getPricebetween(BigDecimal min, BigDecimal max) {
        return  bookMapper.EntityMapperToResponse(bookRepository.filterByPrice(min,max));
    }


}
