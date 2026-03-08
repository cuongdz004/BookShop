package com.example.demo.Service.impl;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Entity.Book;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookSerivceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;


    @Override
    public BookResponse getBookByBookId(Long Id) {
        return bookMapper.MapperOne(bookRepository.findById(Id));
    }

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

    @Override
    public List<BookResponse> getByCate(String name) {
        return  bookMapper.EntityMapperToResponse(bookRepository.findByCategoryName(name));
    }


}
