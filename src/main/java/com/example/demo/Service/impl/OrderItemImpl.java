package com.example.demo.Service.impl;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.OrderItemResponse;
import com.example.demo.Entity.Book;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Mapper.OrderItemMapper;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderItemImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<BookResponse> getTopSellingBooks() {

        Pageable top4 = PageRequest.of(0, 4);

        List<Long> topBookIds = orderItemRepository.findTopSellingBookIds(top4);

        List<Book> books = bookRepository.findByIdIn(topBookIds);

        Map<Long, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));

        List<Book> sortedBooks = topBookIds.stream()
                .map(bookMap::get)
                .filter(Objects::nonNull)
                .toList();

        return bookMapper.EntityMapperToResponse(sortedBooks);
    }
}
